package com.vetweb.model.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class ReportFactory {
	
	private static final Logger LOGGER = Logger.getLogger(ReportFactory.class);
	
	public static Report createReport(ReportType type, Object... values) {
		Report report = null;
		if (type == ReportType.Ocorrencia) {
			Date dataIni = null;
			Date dataFim = null;
			if (values.length != 0) {
				try {
					List<Date> dateParam = new ArrayList<>();
					for (Object paramValue : values) {
						try {
							dateParam.add(new SimpleDateFormat("yyyy-MM-dd").parse((String)paramValue));
						} catch (ClassCastException | ParseException classCastException) {
							LOGGER.error("TIPO DE PARÂMETRO INCORRETO, NÃO FOI POSSÍVEL CONVERTE-LO PARA GERAÇÃO DO RELATÓRIO.");
						}
					}
					for (Date date : dateParam) {
						if (dataIni == null) {
							dataIni = date;
						}
						if (dataFim == null) {
							dataFim = date;
						} else {
							if (dataIni.after(date)) {
								dataIni = date;
							} else {
								dataFim = dataIni;
							}
						}
					}
					report = new ReportOcorrencia(dataIni, dataFim);
				} catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
					LOGGER.error("QUANTIDADE DE PARÂMETROS INCORRETA PASSADOS P/ CRIAÇÃO DO RELATÓRIO.");
				}
			} else {
				report = new ReportOcorrencia(null, null);
			}
		}
		if (type == ReportType.Clientes_por_ano) {
			report = new ReportClientesPorAno();
		}
		return report;
	}

}
