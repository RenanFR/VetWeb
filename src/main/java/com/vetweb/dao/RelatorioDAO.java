package com.vetweb.dao;
//@author renan.rodrigues@metasix.com.br


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vetweb.model.Prontuario;

@Repository
public class RelatorioDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//FIXME Migrar Stream p/ critério de consulta
	public double buscarTotalAReceber() {
		List<Prontuario> prontuarios = entityManager
				.createQuery("SELECT p FROM Prontuario p", Prontuario.class)
				.getResultList();
		double contasReceberAtendimentos = prontuarios.stream()
				.flatMap(prontuario -> prontuario.getAtendimentos().stream())
				.filter(atendimento -> !atendimento.isPago())
				.mapToDouble(atendimento -> atendimento.getTipoDeAtendimento().getCusto().doubleValue())
				.sum();
		double contasReceberVacinas = prontuarios.stream()
				.flatMap(prontuario -> prontuario.getVacinas().stream())
				.filter(vacina -> !vacina.isPago())
				.mapToDouble(vacina -> vacina.getVacina().getPreco().doubleValue())
				.sum();
		return Double.sum(contasReceberAtendimentos, contasReceberVacinas);
	}

}
