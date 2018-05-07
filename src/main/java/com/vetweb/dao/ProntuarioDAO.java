package com.vetweb.dao;
// @author Maria Jéssica

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vetweb.model.Atendimento;
import com.vetweb.model.Prontuario;
import com.vetweb.model.ProntuarioPatologia;
import com.vetweb.model.ProntuarioVacina;
import com.vetweb.model.TipoDeAtendimento;
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
    public List<Prontuario> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Prontuario consultarPorId(long id) {
        return entityManager.find(Prontuario.class, id);
    }

    @Override
    public void remover(Prontuario prontuario) {
        entityManager.remove(prontuario);
    }

    @Override
    public Prontuario consultarPorNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long quantidadeRegistros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Prontuario prontuarioPorAnimal(Long animalId){
        return entityManager.createQuery("SELECT p FROM Prontuario p "
        		+ "LEFT OUTER JOIN p.patologias pat "
                + "LEFT OUTER JOIN p.atendimentos at "
                + "LEFT OUTER JOIN p.vacinas vac "
                + "WHERE p.animal.animalId = :animal", Prontuario.class)
                .setParameter("animal", animalId).getSingleResult();
    }
    
    public List<Vacina> vacinas() {
        return entityManager.createQuery("SELECT v FROM Vacina v", Vacina.class)
                .getResultList();
    }
    
    public List<TipoDeAtendimento> tiposDeAtendimento() {
        return entityManager.createNamedQuery("tiposDeAtendimentoQuery", TipoDeAtendimento.class)
                .getResultList();
    }
    
    public TipoDeAtendimento tipoDeAtendimentoPorNome(String tipoDeAtendimento) {
        return entityManager.createQuery("SELECT t FROM TipoDeAtendimento t WHERE t.nome = :tipoDeAtendimento", TipoDeAtendimento.class)
                .setParameter("tipoDeAtendimento", tipoDeAtendimento)
                .getSingleResult();
    }
    
    public String modeloDoTipoAtendimento(String nomeTipoAtendimento) {
        TipoDeAtendimento tipoDeAtendimento = entityManager.createNamedQuery("tipoDeAtendimentoPorNomeQuery", TipoDeAtendimento.class)
                .setParameter("nomeTipoAtendimento", nomeTipoAtendimento)
                .getSingleResult();
        return tipoDeAtendimento.getModeloAtendimento().toString();
    }
    
    public void adicionarAtendimento(Atendimento atendimento, Long prontuarioId) {
        Prontuario prontuario = consultarPorId(prontuarioId);
        if(prontuario.getAtendimentos().contains(atendimento)) {
        	prontuario.getAtendimentos()
        			.set(prontuario.getAtendimentos().indexOf(atendimento), atendimento);
        } else {
        	prontuario.getAtendimentos().add(atendimento);
        }
        salvar(prontuario);
    }
    
    public void adicionarPatologia(ProntuarioPatologia patologia, Long prontuarioId) {
    	Prontuario prontuario = consultarPorId(prontuarioId);
    	entityManager.persist(patologia);
    	prontuario.getPatologias().add(patologia);
    	salvar(prontuario);
    }
    
    public void adicionarVacina(ProntuarioVacina vacina, Long prontuarioId) {
    	Prontuario prontuario = consultarPorId(prontuarioId);
    	entityManager.persist(vacina);
    	prontuario.getVacinas().add(vacina);
    	salvar(prontuario);
    }
    
    public void removerAtendimentoDoProntuario (Atendimento atendimento, Long prontuarioId) {
    	Prontuario prontuario = consultarPorId(prontuarioId);
    	if(prontuario.getAtendimentos().contains(atendimento)) {
    		prontuario.getAtendimentos().remove(atendimento);
    	} else {
    		throw new RuntimeException("Atendimento " + atendimento.getAtendimentoId() + " nao esta presente no prontuario " + prontuario.getProntuarioId());
    	}
    }
    
    public void removerVacinaDoProntuario (ProntuarioVacina prontuarioVacina, Long prontuarioId) {
    	Prontuario prontuario = consultarPorId(prontuarioId);
    	if(prontuario.getVacinas().contains(prontuarioVacina)) {
    		prontuario.getVacinas().remove(prontuarioVacina);
    	} else {
    		throw new RuntimeException("Vacina " + prontuarioVacina.getVacina().getNome() + " nao esta presente no prontuario " + prontuario.getProntuarioId());
    	}
    }
    
    public ProntuarioVacina buscarOcorrenciaVacinaProntuarioPorId(Long prontuarioVacinaId) {
    	return entityManager.find(ProntuarioVacina.class, prontuarioVacinaId);
    }
    
    public ProntuarioPatologia buscarOcorrenciaPatologiaProntuarioPorId(Long prontuarioPatologiaId) {
    	return entityManager.find(ProntuarioPatologia.class, prontuarioPatologiaId);
    }
    
    public void removerPatologiaDoProntuario (ProntuarioPatologia prontuarioPatologia, Long prontuarioId) {
    	Prontuario prontuario = consultarPorId(prontuarioId);
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
    
    public Atendimento atendimentoPorPreenchimento(String preenchimentoAtendimento) {
    	return entityManager.createQuery("SELECT at FROM Atendimento at WHERE at.preenchimentoModeloAtendimento = :preenchimento", Atendimento.class)
    			.setParameter("preenchimento", preenchimentoAtendimento).getSingleResult();
    }
    
}
