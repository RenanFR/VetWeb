package com.vetweb.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vetweb.model.Animal;
import com.vetweb.model.Atendimento;
import com.vetweb.model.Prontuario;
import com.vetweb.model.ProntuarioVacina;
import com.vetweb.model.Proprietario;

 //@author renanrodrigues
@Repository//Indica que a classe é gerenciada pelo container do Spring e é uma entidade de persistência
public class ProprietarioDAO implements IDAO<Proprietario> {
	
    @PersistenceContext//Especificação da JPA para receber injeção de objeto de contexto com o banco de dados (EntityManager)
    private EntityManager entityManager;
    
    @Autowired
    private AnimalDAO animalDAO;
    
    @Autowired
    private ProntuarioDAO prontuarioDAO;
    
    @Override
    public void salvar(Proprietario proprietario) {
        if(proprietario.getPessoaId() == null)
            entityManager.persist(proprietario);//Spring instancia, injeta e finaliza o EntityManager
        else
            entityManager.merge(proprietario);
    }

    @Override
    public List<Proprietario> listar() {
        return entityManager.createQuery("select p from Proprietario p", Proprietario.class).getResultList();
    }

    @Override
    public Proprietario consultarPorId(long id) {
        return entityManager.find(Proprietario.class, id);
    }

    @Override
    public void remover(Proprietario proprietario) {
        entityManager.remove(proprietario);
    }
    
    public void removerAnimal(long animalId) {
        Animal animal = animalDAO.consultarPorId(animalId);
        Proprietario proprietario = consultarPorId(animal.getProprietario().getPessoaId());
        Animal a = proprietario.getAnimais().stream().filter(animal1 -> animal1.getAnimalId().equals(animalId)).findFirst().get();
        proprietario.getAnimais().remove(a);
        animalDAO.remover(a);
        System.out.println(a.getNome() + a.getAnimalId());
    }

    @Override
    public Proprietario consultarPorNome(String nome) {
        Optional<Proprietario> o = Optional.of(entityManager.createNamedQuery("proprietarioPorNome", Proprietario.class)
                .setParameter("nomeProprietario", nome).getSingleResult());
        Proprietario p = o.orElseThrow(RuntimeException::new);
        return p;
    }

    public void detachProprietario(Proprietario proprietario) {
    	entityManager.detach(proprietario);
    }
    
    @Override
    public long quantidadeRegistros() {
        return entityManager.createNamedQuery("quantidadeClientes", Long.class).getSingleResult();
    }
    
    public List<Prontuario> getProntuariosDosAnimaisDoCliente(Long proprietarioId) {
    	return entityManager.createNamedQuery("prontuariosAnimaisDoCliente", Prontuario.class)
    			.setParameter("Id", proprietarioId)
    			.getResultList();
    }
    
    public List<ProntuarioVacina> getVacinasAplicadasPorCliente(Long proprietario) {
    	return getProntuariosDosAnimaisDoCliente(proprietario)
    			.stream()
    			.flatMap(p -> p.getVacinas().stream()).collect(Collectors.toList());
    }
    
    public List<Atendimento> getAtendimentosRealizadosPorCliente(Long proprietario) {
    	return getProntuariosDosAnimaisDoCliente(proprietario)
    			.stream()
    			.flatMap(p -> p.getAtendimentos().stream()).collect(Collectors.toList());
    }
    
    public BigDecimal getValoresPendentesDoCliente(Proprietario proprietario) {
    	List<Atendimento> atendimentosAnimaisCliente = getAtendimentosRealizadosPorCliente(proprietario.getPessoaId());
    	List<ProntuarioVacina> vacinasAnimaisCliente = getVacinasAplicadasPorCliente(proprietario.getPessoaId());
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
    
    public Set<Proprietario> getClientesEmDebito() {
    	List<BigInteger> idsProprietariosComDebitoVacina = idsClientesComDebitoVacina();
    	List<BigInteger> idsProprietariosComDebitoAtendimento = idsClientesComDebitoAtendimento();
    	Set<Proprietario> proprietariosComDebito = new HashSet<>();
    	idsProprietariosComDebitoVacina.stream().forEach(prop -> proprietariosComDebito.add(consultarPorId(Long.parseLong(prop.toString()))));
    	idsProprietariosComDebitoAtendimento.stream().forEach(prop -> proprietariosComDebito.add(consultarPorId(Long.parseLong(prop.toString()))));
    	return proprietariosComDebito;
    }

	public List<BigInteger> idsClientesComDebitoAtendimento() {
		List<BigInteger> idsProprietariosComDebitoAtendimento = (List<BigInteger>) entityManager.createNativeQuery("SELECT cli.pessoaid FROM proprietarios cli\n" + 
    			"JOIN animais a ON cli.pessoaid = a.proprietario_pessoaid\n" + 
    			"JOIN prontuarios p ON a.prontuario_prontuarioid = p.prontuarioid\n" + 
    			"JOIN prontuarios_atendimento ate ON ate.prontuario_prontuarioid = p.prontuarioid\n" + 
    			"JOIN atendimento atend ON atend.atendimentoid = ate.atendimentos_atendimentoid\n" + 
    			"WHERE atend.pago = FALSE;").getResultList();
		return idsProprietariosComDebitoAtendimento;
	}

	public List<BigInteger> idsClientesComDebitoVacina() {
		List<BigInteger> idsProprietariosComDebitoVacina = (List<BigInteger>) entityManager.createNativeQuery("SELECT cli.pessoaid FROM proprietarios cli\n" + 
    			"JOIN animais a ON cli.pessoaid = a.proprietario_pessoaid\n" + 
    			"JOIN prontuarios p ON a.prontuario_prontuarioid = p.prontuarioid\n" + 
    			"JOIN prontuarios_prontuariovacina vac ON vac.prontuario_prontuarioid = p.prontuarioid\n" + 
    			"JOIN prontuariovacina prvac ON prvac.prontuariovacinaid = vac.vacinas_prontuariovacinaid\n" + 
    			"WHERE prvac.pago = FALSE;").getResultList();
		return idsProprietariosComDebitoVacina;
	}
	
	public List<BigInteger> idsClientesInativosComPagamentoAtendimento() {
		List<BigInteger> clientesInativosComPagamento = (List<BigInteger>) entityManager.createNativeQuery("SELECT cli.pessoaid FROM Proprietarios cli\n" + 
				"JOIN Animais a ON a.proprietario_pessoaid = cli.pessoaid\n" + 
				"JOIN Prontuarios p ON p.prontuarioid = a.prontuario_prontuarioid\n" + 
				"JOIN Prontuarios_Atendimento reg_at ON reg_at.prontuario_prontuarioid = p.prontuarioid\n" + 
				"JOIN Atendimento ate ON ate.atendimentoid = reg_at.atendimentos_atendimentoid\n" + 
				"WHERE cli.ativo = FALSE AND ate.pago = TRUE;").getResultList();
		return clientesInativosComPagamento; 
	}
	
	public List<BigInteger> idsClientesInativosComPagamentoVacina() {
		List<BigInteger> clientesInativosComPagamento = (List<BigInteger>) entityManager.createNativeQuery("SELECT cli.pessoaid FROM Proprietarios cli\n" + 
				"JOIN Animais a ON a.proprietario_pessoaid = cli.pessoaid\n" + 
				"JOIN Prontuarios p ON p.prontuarioid = a.prontuario_prontuarioid\n" + 
				"JOIN prontuarios_prontuariovacina reg_vac ON reg_vac.prontuario_prontuarioid = p.prontuarioid\n" + 
				"JOIN prontuariovacina vac ON vac.prontuariovacinaid = reg_vac.vacinas_prontuariovacinaid\n" + 
				"WHERE cli.ativo = FALSE AND vac.pago = TRUE;").getResultList();
		return clientesInativosComPagamento; 
	}
	
	public Set<Proprietario> getClientesInativosAdimplentes() {
		List<BigInteger> idsClientesInativosComPagamentoAtendimento = idsClientesInativosComPagamentoAtendimento();
		List<BigInteger> idsClientesInativosComPagamentoVacina = idsClientesInativosComPagamentoVacina();
		Set<Proprietario> clientesInativosAdimplentes = new HashSet<>();
		if(idsClientesInativosComPagamentoAtendimento.size() > 0) {
			idsClientesInativosComPagamentoAtendimento.stream()
			.forEach(prop -> clientesInativosAdimplentes.add(consultarPorId(Long.valueOf(prop.toString()))));
		}
		if (idsClientesInativosComPagamentoVacina.size() > 0) {
			idsClientesInativosComPagamentoVacina.stream()
			.forEach(prop -> clientesInativosAdimplentes.add(consultarPorId(Long.valueOf(prop.toString()))));
		}
		return clientesInativosAdimplentes;
	}

}
