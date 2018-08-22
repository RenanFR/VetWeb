package com.vetweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vetweb.model.Documento;
import com.vetweb.model.ModeloDocumento;

@Repository
public class DocumentoDAO implements IDAO<Documento>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void salvar(Documento documento) {
		if (documento.getDocumentoId() == null) {
			entityManager.persist(documento);
		} else {
			entityManager.merge(documento);
		}
	}
	
	public void salvarModeloDocumento(ModeloDocumento modeloDocumento) {
		if (modeloDocumento.getModelodocumentoId() == null) {
			entityManager.persist(modeloDocumento);
		} else {
			entityManager.merge(modeloDocumento);
		}
	}

	@Override
	public List<Documento> listarTodos() {
		return entityManager
				.createQuery("SELECT documento FROM Documento documento", Documento.class)
				.getResultList();
	}
	
	public List<ModeloDocumento> listarTodosModelos() {
		return entityManager
				.createQuery("SELECT modelo FROM ModeloDocumento modelo", ModeloDocumento.class)
				.getResultList();
	}

	@Override
	public Documento buscarPorId(long id) {
		return entityManager
				.find(Documento.class, id);
	}
	
	public ModeloDocumento buscarModeloPorId(long id) {
		return entityManager
				.find(ModeloDocumento.class, id);
	}

	@Override
	public void remover(Documento documento) {
		entityManager.remove(documento);
	}
	
	public void removerModelo(ModeloDocumento modeloDocumento) {
		entityManager.remove(modeloDocumento);
	}

}
