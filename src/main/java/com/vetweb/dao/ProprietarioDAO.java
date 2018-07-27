package com.vetweb.dao;

//@author renan.rodrigues@metasix.com.br

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vetweb.model.Animal;
import com.vetweb.model.Atendimento;
import com.vetweb.model.Prontuario;
import com.vetweb.model.ProntuarioVacina;
import com.vetweb.model.Proprietario;

@Repository
public class ProprietarioDAO implements IDAO<Proprietario> {
	
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private AnimalDAO animalDAO;
    
    @Autowired
    private ProntuarioDAO prontuarioDAO;
    
    @SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ProprietarioDAO.class);
    
    @Override
    public void salvar(Proprietario proprietario) {
        if(proprietario.getPessoaId() == null)
            entityManager.persist(proprietario);
        else
            entityManager.merge(proprietario);
    }

    @Override
    public List<Proprietario> listarTodos() {
        return entityManager.createQuery("select p from Proprietario p", Proprietario.class).getResultList();
    }

    @Override
    public Proprietario buscarPorId(long id) {
        return entityManager.find(Proprietario.class, id);
    }

    @Override
    public void remover(Proprietario proprietario) {
        entityManager.remove(proprietario);
    }
    
    public void removerAnimal(long animalId) {
        Animal animal = animalDAO.buscarPorId(animalId);
        Proprietario proprietario = buscarPorId(animal.getProprietario().getPessoaId());
        Animal a = proprietario
        		.getAnimais()
        		.stream()
        		.filter(animal1 -> animal1.getAnimalId().equals(animalId))
        		.findFirst()
        		.get();
        proprietario.getAnimais().remove(a);
        animalDAO.remover(a);
    }
    //FIXME Remover tratamento opcional
    public Proprietario buscarPorNome(String nome) {
        Optional<Proprietario> o = Optional
        		.of(entityManager
        				.createNamedQuery("proprietarioPorNome", Proprietario.class)
        				.setParameter("nomeProprietario", nome).getSingleResult());
        Proprietario p = o.orElseThrow(RuntimeException::new);
        return p;
    }

    public void detach(Proprietario proprietario) {
    	entityManager.detach(proprietario);
    }
    
    public long buscarQuantidade() {
        return entityManager
        		.createNamedQuery("quantidadeClientes", Long.class)
        		.getSingleResult();
    }
    
    public List<Prontuario> buscarProntuariosParaOCliente(Long proprietarioId) {
    	return entityManager.createNamedQuery("prontuariosAnimaisDoCliente", Prontuario.class)
    			.setParameter("Id", proprietarioId)
    			.getResultList();
    }
    
    //FIXME Migrar Stream p/ critério na consulta
    public List<ProntuarioVacina> buscarVacinasParaOCliente(Long proprietario) {
    	return buscarProntuariosParaOCliente(proprietario)
    			.stream()
    			.flatMap(p -> p.getVacinas().stream()).collect(Collectors.toList());
    }
    
    //FIXME Migrar Stream p/ critério na consulta    
    public List<Atendimento> getAtendimentosRealizadosPorCliente(Long proprietario) {
    	return buscarProntuariosParaOCliente(proprietario)
    			.stream()
    			.flatMap(p -> p.getAtendimentos().stream()).collect(Collectors.toList());
    }
    
    //FIXME Migrar Stream p/ critério na consulta    
    public BigDecimal buscarValorPendenteDoCliente(Proprietario proprietario) {
    	List<Atendimento> atendimentosAnimaisCliente = getAtendimentosRealizadosPorCliente(proprietario.getPessoaId());
    	List<ProntuarioVacina> vacinasAnimaisCliente = buscarVacinasParaOCliente(proprietario.getPessoaId());
    	BigDecimal totalPendente;
    	double totalPendenteAtendimentos = atendimentosAnimaisCliente.stream()
    			.filter(at -> !at.isPago())
    			.mapToDouble(a -> a.getTipoDeAtendimento().getCusto().doubleValue())
    			.sum();
    	double totalPendenteVacinas = vacinasAnimaisCliente.stream()
    			.filter(vac -> !vac.isPago())
    			.mapToDouble(v -> v.getVacina().getPreco().doubleValue())
    			.sum();
    	totalPendente = new BigDecimal(totalPendenteAtendimentos + totalPendenteVacinas);    
    	return totalPendente;
    }

	public List<Proprietario> buscarClientesEmDebito() {
		String query = "SELECT p FROM Proprietario p "
			+ "JOIN p.animais a "
			+ "JOIN a.prontuario pr "
			+ "LEFT JOIN pr.vacinas v "
			+ "LEFT JOIN pr.atendimentos a "
			+ "WHERE v.pago = false OR a.pago = false";
		List<Proprietario> clientesComDebito = entityManager
												.createQuery(query, Proprietario.class)
												.getResultList();
		return clientesComDebito;
	}
	
	public Set<Proprietario> buscarClientesInativosAdimplentes() {
		List<Prontuario> prontuariosDeClientesInativos = entityManager
				.createQuery("SELECT p FROM Prontuario p JOIN p.animal a JOIN a.proprietario cli WHERE cli.ativo = FALSE", Prontuario.class)
				.getResultList();
		Set<Proprietario> clientesInativosAdimplentes = new HashSet<>();
		for (Proprietario proprietario : prontuariosDeClientesInativos.stream().map(p -> p.getAnimal().getProprietario()).collect(Collectors.toList())) {
			boolean todasAsVacinasPagas = prontuariosDeClientesInativos
					.stream()
					.flatMap(p -> p.getVacinas().stream())
					.filter(vac -> vac.getProntuario().getAnimal().getProprietario().equals(proprietario))
					.allMatch(vac -> vac.isPago());
			boolean todosOsAtendimentosPagos =	prontuariosDeClientesInativos
					.stream()
					.flatMap(p -> p.getAtendimentos().stream())
					.filter(ate -> prontuarioDAO.buscarProntuarioDoAtendimento(ate).getAnimal().getProprietario().equals(proprietario))
					.allMatch(ate -> ate.isPago());
			if (todasAsVacinasPagas && todosOsAtendimentosPagos) {
				clientesInativosAdimplentes.add(proprietario);				
			}
			return clientesInativosAdimplentes;
		}
		return clientesInativosAdimplentes;
	}
	
}
