package com.vetweb.dao;
//@author renan.rodrigues@metasix.com.br


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vetweb.model.Atendimento;
import com.vetweb.model.Prontuario;
import com.vetweb.model.ProntuarioPatologia;
import com.vetweb.model.ProntuarioVacina;
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
    
    public void adicionarAtendimento(Atendimento atendimento, Long prontuarioId) {
        Prontuario prontuario = buscarPorId(prontuarioId);
        if(prontuario.getAtendimentos().contains(atendimento)) {
        	prontuario.getAtendimentos()
        			.set(prontuario.getAtendimentos().indexOf(atendimento), atendimento);
        } else {
        	prontuario.getAtendimentos().add(atendimento);
        }
        salvar(prontuario);
    }
    
    public void adicionarPatologia(ProntuarioPatologia patologia, Long prontuarioId) {
    	Prontuario prontuario = buscarPorId(prontuarioId);
    	salvarPatologia(patologia);
    	if (!prontuario.getPatologias().contains(patologia)) {
    		prontuario.getPatologias().add(patologia);
    	} else {
    		prontuario.getPatologias()
    			.set(prontuario.getPatologias().indexOf(patologia), patologia);
    	}
    	salvar(prontuario);
    }

	private void salvarPatologia(ProntuarioPatologia patologia) {
		if (patologia.getProntuarioPatologiaId() == null) {
    		entityManager.persist(patologia);
    	}
    	else {
    		entityManager.merge(patologia);
    	}
	}
    
    public void adicionarVacina(ProntuarioVacina vacina, Long prontuarioId) {
    	Prontuario prontuario = buscarPorId(prontuarioId);
    	salvarVacina(vacina);
    	if (!prontuario.getVacinas().contains(vacina))
    		prontuario.getVacinas().add(vacina);
    	else
    		prontuario.getVacinas()
    			.set(prontuario.getVacinas().indexOf(vacina), vacina);
    	salvar(prontuario);
    }

	private void salvarVacina(ProntuarioVacina vacina) {
		if (vacina.getProntuarioVacinaId() == null)
    		entityManager.persist(vacina);
    	else
    		entityManager.merge(vacina);
	}
    
    public void removerAtendimento (Atendimento atendimento, Long prontuarioId) {
    	Prontuario prontuario = buscarPorId(prontuarioId);
    	if(prontuario.getAtendimentos().contains(atendimento)) {
    		prontuario.getAtendimentos().remove(atendimento);
    	} else {
    		throw new RuntimeException("Atendimento " + atendimento.getAtendimentoId() + " nao esta presente no prontuario " + prontuario.getProntuarioId());
    	}
    }
    
    public void removerVacina (ProntuarioVacina prontuarioVacina, Long prontuarioId) {
    	Prontuario prontuario = buscarPorId(prontuarioId);
    	if(prontuario.getVacinas().contains(prontuarioVacina)) {
    		prontuario.getVacinas().remove(prontuarioVacina);
    	} else {
    		throw new RuntimeException("Vacina " + prontuarioVacina.getVacina().getNome() + " nao esta presente no prontuario " + prontuario.getProntuarioId());
    	}
    }
    
    public ProntuarioVacina buscarOcorrenciaDaVacina(Long prontuarioVacinaId) {
    	return entityManager
    			.find(ProntuarioVacina.class, prontuarioVacinaId);
    }
    
    public ProntuarioPatologia buscarOcorrenciaDaPatologia(Long prontuarioPatologiaId) {
    	return entityManager
    			.find(ProntuarioPatologia.class, prontuarioPatologiaId);
    }
    
    public void removerPatologia (ProntuarioPatologia prontuarioPatologia, Long prontuarioId) {
    	Prontuario prontuario = buscarPorId(prontuarioId);
    	if(prontuario.getPatologias().contains(prontuarioPatologia)) {
    		prontuario.getPatologias().remove(prontuarioPatologia);
    	} else {
    		throw new RuntimeException("Patologia " + prontuarioPatologia.getPatologia().getNome() + " nao esta presente no prontuario " + prontuario.getProntuarioId());
    	}
    }
    
    public void salvarAtendimento(Atendimento atendimento) {
        if(atendimento.getAtendimentoId() == null)
            entityManager.persist(atendimento);
        else
            entityManager.merge(atendimento);
    }
    
    public Atendimento buscarAtendimentoPorPreenchimento(String preenchimentoAtendimento) {
    	return entityManager
    			.createQuery("SELECT at FROM Atendimento at WHERE at.preenchimentoModeloAtendimento = :preenchimento", Atendimento.class)
    			.setParameter("preenchimento", preenchimentoAtendimento)
    			.getSingleResult();
    }
	
	public Prontuario buscarProntuarioDoAtendimento(Atendimento atendimento) {
		return entityManager
				.createQuery("SELECT p FROM Prontuario p WHERE :at MEMBER OF p.atendimentos", Prontuario.class)
				.setParameter("at", atendimento)
				.getSingleResult();
	}
    
}
