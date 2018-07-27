package com.vetweb.dao;
//	@author renan.rodrigues@metasix.com.br

import com.vetweb.model.TipoDeAtendimento;
import com.vetweb.model.Atendimento;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class AtendimentoDAO implements IDAO<Atendimento> {
	
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void salvar(Atendimento atendimento) {
    	if(atendimento.getAtendimentoId() == null)
    		entityManager.persist(atendimento);
    	else 
    		entityManager.merge(atendimento);
    }

    @Override
    public List<Atendimento> listarTodos() {
        return entityManager
        		.createQuery("SELECT a FROM Atendimento a", Atendimento.class)
        		.getResultList();
    }

    @Override
    public Atendimento buscarPorId(long id) {
    	return entityManager
    			.find(Atendimento.class, id);
    }

    @Override
    public void remover(Atendimento atendimento) {
        entityManager.remove(atendimento);
    }
    
    public void salvarTipoDeAtendimento(TipoDeAtendimento tipoDeAtendimento){
        if(tipoDeAtendimento.getTipoDeAtendimentoId() == null) {
            entityManager.persist(tipoDeAtendimento);
        } else {
            entityManager.merge(tipoDeAtendimento);
        }
    }
    
    public List<TipoDeAtendimento> buscarTiposDeAtendimento(){
        return entityManager
        		.createNamedQuery("tiposDeAtendimentoQuery", TipoDeAtendimento.class)
        		.getResultList();
    }
    
    public TipoDeAtendimento buscarTipoDeAtendimentoPorId(Long tipoDeAtendimentoId) {
        return entityManager
        		.find(TipoDeAtendimento.class, tipoDeAtendimentoId);
    }
    
    public TipoDeAtendimento buscarTipoDeAtendimentoPorNome(String tipoDeAtendimento) {
        return entityManager
        		.createNamedQuery("tipoDeAtendimentoPorNomeQuery", TipoDeAtendimento.class)
                .setParameter("tipoDeAtendimento", tipoDeAtendimento)
                .getSingleResult();
    }
    
    public String buscarModeloDoTipoDeAtendimento(String tipoDeAtendimento) {
        TipoDeAtendimento tipo = buscarTipoDeAtendimentoPorNome(tipoDeAtendimento);
        return tipo.getModeloAtendimento().toString();
    }    
    
    public void removerTipoDeAtendimento(TipoDeAtendimento tipoDeAtendimento) {
        entityManager.remove(tipoDeAtendimento);
    }
    
}
