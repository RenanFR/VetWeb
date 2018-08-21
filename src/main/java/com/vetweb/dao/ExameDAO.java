package com.vetweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vetweb.model.Exame;

@Repository
public class ExameDAO implements IDAO<Exame>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void salvar(Exame exame) {
		if (exame.getExameId() == null) {
			entityManager.persist(exame);
		} else {
			entityManager.merge(exame);
		}
		
	}

	@Override
	public List<Exame> listarTodos() {
		return entityManager
				.createQuery("SELECT exame FROM Exame exame", Exame.class)
				.getResultList();
	}

	@Override
	public Exame buscarPorId(long id) {
		return entityManager
				.find(Exame.class, id);
	}

	@Override
	public void remover(Exame exame) {
		entityManager.remove(exame);
	}

}
