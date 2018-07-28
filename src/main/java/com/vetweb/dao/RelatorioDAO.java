package com.vetweb.dao;
//@author renan.rodrigues@metasix.com.br


import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class RelatorioDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//FIXME Migrar Stream p/ critério de consulta
	public BigDecimal buscarTotalAReceber() {
		String consultaTotalPendente = "SELECT SUM (at.tipoDeAtendimento.custo) FROM Atendimento at "
										+ "JOIN at.tipoDeAtendimento tipo "
										+ "WHERE at.pago = FALSE";
		TypedQuery<BigDecimal> queryTotalPendente = entityManager
				.createQuery(consultaTotalPendente, BigDecimal.class);
		return queryTotalPendente.getSingleResult();
	}

}
