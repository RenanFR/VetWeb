package com.vetweb.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vetweb.model.Agendamento;

@Repository
public class AgendamentoDAO implements IDAO<Agendamento>{
	
	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Agendamento agendamento) {
		if (!listarTodos(agendamento.getDataHoraInicial(), agendamento.getDataHoraFinal()).isEmpty()) {
			throw new RuntimeException("O AGENDAMENTO CONFLITA COM OUTRO EXISTENTE NO MESMO HORÁRIO.");
		}
		if (agendamento.getAgendamentoId() == null) {
			entityManager.persist(agendamento);
		} else {
			entityManager.merge(agendamento);
		}
	}

	@Override
	public List<Agendamento> listarTodos() {
		return entityManager
					.createQuery("SELECT agendamento FROM Agendamento agendamento", Agendamento.class)
					.getResultList();
	}
	
	public List<Agendamento> listarTodos(LocalDateTime dataInicialFiltro, LocalDateTime dataFinalFiltro) {
		return entityManager
				.createQuery("SELECT agendamento FROM Agendamento agendamento "
						+ "WHERE agendamento.dataHoraInicial BETWEEN :dataHoraInicio AND :dataHoraFim", Agendamento.class)
				.setParameter("dataHoraInicio", dataInicialFiltro)
				.setParameter("dataHoraFim", dataFinalFiltro)
				.getResultList();
	}

	@Override
	public Agendamento buscarPorId(long id) {
		return entityManager
				.find(Agendamento.class, id);
	}
	
	public Agendamento buscarPorIdOcorrencia(long id) {
		String consulta = "SELECT agendamento FROM Agendamento agendamento "
				+ "WHERE agendamento.ocorrencia.ocorrenciaId = :codigoOcorrencia";
		try {
			return entityManager
					.createQuery(consulta, Agendamento.class)
					.setParameter("codigoOcorrencia", id)
					.getSingleResult();
		} catch (NoResultException noResultException) {
			return null;
		}
	}

	@Override
	public void remover(Agendamento agendamento) {
		entityManager.remove(agendamento);
	}
	
	
}
