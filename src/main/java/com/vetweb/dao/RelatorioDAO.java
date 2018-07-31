package com.vetweb.dao;
//@author renan.rodrigues@metasix.com.br


import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class RelatorioDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public BigDecimal buscarTotalAReceber() {
		String consultaTotalPendenteEmAtendimentos = "SELECT SUM (tipo.custo) "
				+ "FROM TipoDeAtendimento tipo "
				+ "JOIN tipo.atendimentos atendimento "
				+ "WHERE atendimento.pago = false";
		String consultaTotalPendenteEmVacinas = "SELECT SUM (vacina.preco) "
				+ "FROM Vacina vacina "
				+ "JOIN vacina.ocorrenciasVacina prontuarioVac "
				+ "WHERE prontuarioVac.pago = false";
		TypedQuery<BigDecimal> queryTotalPendenteEmAtendimentos = entityManager
				.createQuery(consultaTotalPendenteEmAtendimentos, BigDecimal.class);
		TypedQuery<BigDecimal> queryTotalPendenteEmVacinas = entityManager
				.createQuery(consultaTotalPendenteEmVacinas, BigDecimal.class);
		BigDecimal totalPendenteEmAtendimentos = Optional.ofNullable(queryTotalPendenteEmAtendimentos.getSingleResult()).orElse(new BigDecimal(0));
		BigDecimal totalPendenteEmVacinas = Optional.ofNullable(queryTotalPendenteEmVacinas.getSingleResult()).orElse(new BigDecimal(0));
		BigDecimal totalPendenteGeral = new BigDecimal(0);
		totalPendenteGeral = totalPendenteEmAtendimentos.add(totalPendenteEmVacinas);
		return totalPendenteGeral;
	}

}
