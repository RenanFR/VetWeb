package com.vetweb.dao;
//@author renan.rodrigues@metasix.com.br


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vetweb.model.OcorrenciaAtendimento;
import com.vetweb.model.OcorrenciaExame;
import com.vetweb.model.Prontuario;
import com.vetweb.model.OcorrenciaPatologia;
import com.vetweb.model.OcorrenciaVacina;
import com.vetweb.model.Vacina;

@Repository
public class ProntuarioDAO implements IDAO<Prontuario>{
	
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void salvar(Prontuario prontuario) {
        if (prontuario.getProntuarioId() == null) {
            entityManager.persist(prontuario);
        } else {
            entityManager.merge(prontuario);
        }
    }

    @Override
    public List<Prontuario> listarTodos() {
        return entityManager.createQuery("SELECT p FROM Prontuario p "
        		+ "LEFT OUTER JOIN p.patologias pat "
                + "LEFT OUTER JOIN p.atendimentos at "
                + "LEFT OUTER JOIN p.vacinas vac ", Prontuario.class)
                .getResultList();
    }

    @Override
    public Prontuario buscarPorId(long id) {
        return entityManager
        		.find(Prontuario.class, id);
    }

    @Override
    public void remover(Prontuario prontuario) {
        entityManager.remove(prontuario);
    }
    
    public Prontuario buscarProntuarioPorAnimal(Long animalId){
        return entityManager.createQuery("SELECT p FROM Prontuario p "
        		+ "LEFT OUTER JOIN p.patologias pat "
                + "LEFT OUTER JOIN p.atendimentos at "
                + "LEFT OUTER JOIN p.vacinas vac "
                + "WHERE p.animal.animalId = :animal", Prontuario.class)
                .setParameter("animal", animalId)
                .getSingleResult();
    }
    
    public List<Vacina> buscarVacinas() {
        return entityManager
        		.createQuery("SELECT v FROM Vacina v", Vacina.class)
                .getResultList();
    }
    
	public void salvarOcorrenciaPatologia(OcorrenciaPatologia patologia) {
		if (patologia.getOcorrenciaId() == null) {
    		entityManager.persist(patologia);
    	}
    	else {
    		entityManager.merge(patologia);
    	}
	}
	
	public void salvarOcorrenciaExame(OcorrenciaExame exame) {
		if (exame.getOcorrenciaId() == null) {
			entityManager.persist(exame);
		}
		else {
			entityManager.merge(exame);
		}
	}

	public void salvarOcorrenciaVacina(OcorrenciaVacina vacina) {
		if (vacina.getOcorrenciaId() == null)
    		entityManager.persist(vacina);
    	else
    		entityManager.merge(vacina);
	}
    
    public void removerAtendimento (OcorrenciaAtendimento atendimento) {
    	entityManager.remove(atendimento);
    }
    
    public void removerOcorrenciaVacina (OcorrenciaVacina prontuarioVacina) {
    	entityManager.remove(prontuarioVacina);
    }
    
    public OcorrenciaVacina buscarOcorrenciaVacina(Long prontuarioVacinaId) {
    	return entityManager
    			.find(OcorrenciaVacina.class, prontuarioVacinaId);
    }
    
    public Vacina buscarVacinaPorId(Long vacinaId) {
    	return entityManager
    			.find(Vacina.class, vacinaId);
    }
    
    public List<OcorrenciaVacina> buscarTodasOcorrenciasVacina() {
    	String consultaTodasOcorrenciasVacina = "SELECT ocorrenciaVacina FROM OcorrenciaVacina ocorrenciaVacina";
    	return entityManager
    			.createQuery(consultaTodasOcorrenciasVacina, OcorrenciaVacina.class)
    			.getResultList();
    }
    
    public List<OcorrenciaExame> buscarTodasOcorrenciasExame(){
    	String consultaTodasOcorrenciasExame = "SELECT ocorrenciaExame FROM OcorrenciaExame ocorrenciaExame";
    	return entityManager
    			.createQuery(consultaTodasOcorrenciasExame, OcorrenciaExame.class)
    			.getResultList();
    }
    
    public OcorrenciaPatologia buscarOcorrenciaPatologia(Long prontuarioPatologiaId) {
    	return entityManager
    			.find(OcorrenciaPatologia.class, prontuarioPatologiaId);
    }
    
    public OcorrenciaExame buscarOcorrenciaExame(Long ocorrenciaexameId) {
    	return entityManager
    			.find(OcorrenciaExame.class, ocorrenciaexameId);
    }
    
    public void removerOcorrenciaExame (OcorrenciaExame ocorrenciaExame) {
    	entityManager.remove(ocorrenciaExame);
    }
    
    public void removerOcorrenciaPatologia (OcorrenciaPatologia prontuarioPatologia) {
    	entityManager.remove(prontuarioPatologia);
    }
    
    public void salvarAtendimento(OcorrenciaAtendimento atendimento) {
        if(atendimento.getOcorrenciaId() == null)
            entityManager.persist(atendimento);
        else
            entityManager.merge(atendimento);
    }
    
    public OcorrenciaAtendimento buscarAtendimentoPorPreenchimento(String preenchimentoAtendimento) {
    	return entityManager
    			.createQuery("SELECT ocorrencia FROM OcorrenciaAtendimento ocorrencia WHERE ocorrencia.preenchimentoModeloAtendimento = :preenchimento", OcorrenciaAtendimento.class)
    			.setParameter("preenchimento", preenchimentoAtendimento)
    			.getSingleResult();
    }
	
	public Prontuario buscarProntuarioDoAtendimento(OcorrenciaAtendimento atendimento) {
		return entityManager
				.createQuery("SELECT p FROM Prontuario p WHERE :at MEMBER OF p.atendimentos", Prontuario.class)
				.setParameter("at", atendimento)
				.getSingleResult();
	}
    
}
