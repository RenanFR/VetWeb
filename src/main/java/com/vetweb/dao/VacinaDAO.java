package com.vetweb.dao;
//@author renan.rodrigues@metasix.com.br

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vetweb.model.Vacina;

@Repository
public class VacinaDAO implements IDAO<Vacina>{
	
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void salvar(Vacina vacina) {
        if(vacina.getVacinaId() == null)
            entityManager.persist(vacina);
        else
            entityManager.merge(vacina);
    }

    @Override
    public List<Vacina> listarTodos() {
        return entityManager.createQuery("SELECT v FROM Vacina v", Vacina.class)
                .getResultList();
    }

    @Override
    public Vacina buscarPorId(long vacinaId) {
        return entityManager.find(Vacina.class, vacinaId);
    }

    @Override
    public void remover(Vacina vacina) {
        entityManager.remove(vacina);
    }

    public Vacina buscarPorNome(String nomeVacina) {
        return entityManager
        		.createQuery("SELECT v FROM Vacina v WHERE v.nome = :nomeVacina", Vacina.class)
                .setParameter("nomeVacina", nomeVacina)
                .getSingleResult();
    }

}
