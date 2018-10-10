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
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vetweb.model.Animal;
import com.vetweb.model.OcorrenciaAtendimento;
import com.vetweb.model.OcorrenciaExame;
import com.vetweb.model.OcorrenciaVacina;
import com.vetweb.model.Prontuario;
import com.vetweb.model.Proprietario;
import com.vetweb.model.pojo.TipoOcorrenciaProntuario;

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
        return entityManager.createQuery("SELECT p FROM Proprietario p", Proprietario.class).getResultList();
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
        proprietario.getAnimais().remove(animal);
        animalDAO.remover(animal);
    }
    
    public Proprietario buscarPorNome(String nome) {
        Proprietario proprietario = entityManager
			.createNamedQuery("proprietarioPorNome", Proprietario.class)
			.setParameter("nomeProprietario", nome).getSingleResult();
        return proprietario;
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
    	String consultaProntuariosParaOCliente = "SELECT prontuario FROM Prontuario prontuario "
    			+ "JOIN prontuario.vacinas ocorrenciaVacina "
    			+ "JOIN prontuario.patologias ocorrenciaPatologia "
    			+ "JOIN prontuario.atendimentos ocorrenciaAtendimento "
    			+ "JOIN prontuario.animal animal "
    			+ "JOIN animal.proprietario cliente "
    			+ "WHERE cliente.pessoaId = :codigoCliente";
    	return entityManager
    			.createQuery(consultaProntuariosParaOCliente, Prontuario.class)
    			.setParameter("codigoCliente", proprietarioId)
    			.getResultList();
    }
    
    public List<OcorrenciaVacina> buscarVacinasParaOCliente(Long proprietario) {
    	String consultaVacinasParaOCliente = "SELECT ocorrenciaVacina FROM OcorrenciaVacina ocorrenciaVacina "
    			+ "JOIN ocorrenciaVacina.prontuario prontuario "
    			+ "JOIN prontuario.animal animal "
    			+ "JOIN animal.proprietario cliente "
    			+ "WHERE cliente.pessoaId = :codigoCliente";
    	return entityManager
    			.createQuery(consultaVacinasParaOCliente, OcorrenciaVacina.class)
    			.setParameter("codigoCliente", proprietario)
    			.getResultList();
    }
    
    public List<OcorrenciaExame> buscarExamesParaOCliente(Long proprietario) {
    	String consultaExamesParaOCliente = "SELECT ocorrenciaExame FROM OcorrenciaExame ocorrenciaExame "
    			+ "JOIN ocorrenciaExame.prontuario prontuario "
    			+ "JOIN prontuario.animal animal "
    			+ "JOIN animal.proprietario cliente "
    			+ "WHERE cliente.pessoaId = :codigoCliente";
    	return entityManager
    			.createQuery(consultaExamesParaOCliente, OcorrenciaExame.class)
    			.setParameter("codigoCliente", proprietario)
    			.getResultList();
    }
    
    public List<OcorrenciaAtendimento> buscarAtendimentosParaOCliente(Long proprietario) {
    	String consultaAtendimentosParaOCliente = "SELECT ocorrenciaAtendimento FROM OcorrenciaAtendimento ocorrenciaAtendimento "
    			+ "JOIN ocorrenciaAtendimento.prontuario prontuario "
    			+ "JOIN prontuario.animal animal "
    			+ "JOIN animal.proprietario cliente "
    			+ "WHERE cliente.pessoaId = :codigoCliente";
    	return entityManager
    			.createQuery(consultaAtendimentosParaOCliente, OcorrenciaAtendimento.class)
    			.setParameter("codigoCliente", proprietario)
    			.getResultList();
    }
    
    public List<Animal> buscarAnimaisDoCliente(Long proprietario) {
    	String consultaAnimaisDoCliente = "SELECT DISTINCT(animal) FROM Animal animal "
    			+ "JOIN animal.proprietario proprietario "
    			+ "WHERE proprietario.pessoaId = :codigoCliente";
    	return entityManager
    			.createQuery(consultaAnimaisDoCliente, Animal.class)
    			.setParameter("codigoCliente", proprietario)
    			.getResultList();
    }
    
    public BigDecimal buscarValorPendenteDoCliente(Proprietario proprietario) {
    	String consultaValorPendenteEmVacinas = "SELECT SUM (vacina.preco) FROM Vacina vacina "
		    	+ "JOIN vacina.ocorrenciasVacina ocorrenciaVacina "
		    	+ "JOIN ocorrenciaVacina.prontuario prontuario "
		    	+ "JOIN prontuario.animal animal "
		    	+ "JOIN animal.proprietario cliente "
		    	+ "WHERE cliente.pessoaId = :codigoCliente "
		    	+ "AND ocorrenciaVacina.pago = false";
    	String consultaValorPendenteEmAtendimentos = "SELECT SUM (tipo.custo) FROM TipoDeAtendimento tipo "
    			+ "JOIN tipo.atendimentos atendimento "
    			+ "JOIN atendimento.prontuario prontuario "
    			+ "JOIN prontuario.animal animal "
    			+ "JOIN animal.proprietario cliente "
    			+ "WHERE cliente.pessoaId = :codigoCliente "
    			+ "AND atendimento.pago = false";
    	TypedQuery<BigDecimal> queryValorPendenteEmVacinas = entityManager
    			.createQuery(consultaValorPendenteEmVacinas, BigDecimal.class)
    			.setParameter("codigoCliente", proprietario.getPessoaId());
    	TypedQuery<BigDecimal> queryValorPendenteEmAtendimentos = entityManager
    			.createQuery(consultaValorPendenteEmAtendimentos, BigDecimal.class)
    			.setParameter("codigoCliente", proprietario.getPessoaId());;
    	BigDecimal totalPendenteEmAtendimentos = Optional.ofNullable(queryValorPendenteEmAtendimentos.getSingleResult()).orElse(new BigDecimal(0));
    	BigDecimal totalPendenteEmVacinas = Optional.ofNullable(queryValorPendenteEmVacinas.getSingleResult()).orElse(new BigDecimal(0));
    	return totalPendenteEmAtendimentos.add(totalPendenteEmVacinas);
    	
    }

	public List<Proprietario> buscarClientesEmDebito(TipoOcorrenciaProntuario... tipoOcorrenciaProntuario) {
		StringBuilder query = new StringBuilder("SELECT p FROM Proprietario p "
			+ "JOIN p.animais a "
			+ "JOIN a.prontuario pr ");
			if (tipoOcorrenciaProntuario.length > 0) {
				for (TipoOcorrenciaProntuario tipoOcorrencia : tipoOcorrenciaProntuario) {
					if (tipoOcorrencia == TipoOcorrenciaProntuario.VACINA) {
						query.append("LEFT JOIN pr.vacinas v ");
					} else if (tipoOcorrencia == TipoOcorrenciaProntuario.ATENDIMENTO) {
						query.append("LEFT JOIN pr.atendimentos a ");
					} else if (tipoOcorrencia == TipoOcorrenciaProntuario.EXAME) {
						query.append("LEFT JOIN pr.exames e ");
					}
				}
			} else {
				query.append("LEFT JOIN pr.vacinas v ");
				query.append("LEFT JOIN pr.atendimentos a ");
				query.append("LEFT JOIN pr.exames e ");
			}
			query.append("WHERE ");
			if (tipoOcorrenciaProntuario.length > 0) {
				for (TipoOcorrenciaProntuario tipoOcorrencia : tipoOcorrenciaProntuario) {
					if (tipoOcorrencia == TipoOcorrenciaProntuario.VACINA) {
						query.append("v.pago = false ");
					}
					if (tipoOcorrencia == TipoOcorrenciaProntuario.ATENDIMENTO) {
						if (query.toString().contains("v.pago")) {
							query.append("OR ");
						}
						query.append("a.pago = false ");
					}
					if (tipoOcorrencia == TipoOcorrenciaProntuario.EXAME) {
						if (query.toString().contains("e.pago")) {
							query.append("OR ");
						}
						query.append("e.pago = false ");
					}
				}
			} else {
				query.append("v.pago = false OR a.pago = false OR e.pago = false");
			}
		List<Proprietario> clientesComDebito = entityManager
												.createQuery(query.toString(), Proprietario.class)
												.getResultList();
		return clientesComDebito;
	}
	
	public List<Proprietario> buscarClientesInativos() {
		String query = "SELECT p FROM Proprietario p "
				+ "WHERE p.ativo = FALSE";
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
