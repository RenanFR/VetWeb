package com.vetweb.dao;
//	@author renan.rodrigues@metasix.com.br

import com.vetweb.model.TipoDeAtendimento;
import com.vetweb.model.OcorrenciaAtendimento;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class AtendimentoDAO implements IDAO<OcorrenciaAtendimento> {
	
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void salvar(OcorrenciaAtendimento atendimento) {
    	if(atendimento.getOcorrenciaId() == null)
    		entityManager.persist(atendimento);
    	else 
    		entityManager.merge(atendimento);
    }

    @Override
    public List<OcorrenciaAtendimento> listarTodos() {
        return entityManager
        		.createQuery("SELECT ocorrencia FROM OcorrenciaAtendimento ocorrencia", OcorrenciaAtendimento.class)
        		.getResultList();
    }

    @Override
    public OcorrenciaAtendimento buscarPorId(long id) {
    	return entityManager
    			.find(OcorrenciaAtendimento.class, id);
    }

    @Override
    public void remover(OcorrenciaAtendimento atendimento) {
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
