package com.vetweb.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vetweb.model.Agendamento;

@Repository
public class AgendamentoDAO implements IDAO<Agendamento>{
	
	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Agendamento agendamento) {
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

	@Override
	public void remover(Agendamento agendamento) {
		entityManager.remove(agendamento);
	}
	
	
}
