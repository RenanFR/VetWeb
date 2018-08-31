package com.vetweb.model.report;

import java.util.Date;

public class ReportFactory {
	
	public static Report createReport(ReportType type, Object... values) {
		Report report = null;
		if (type == ReportType.Ocorrencia) {
			Date dataIni = null;
			Date dataFim = null;
			try {
				dataIni = (Date)values[0];
				dataFim = (Date)values[1];
			} catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
				
			}
			report = new ReportOcorrencia(dataIni, dataFim);
		}
		return report;
	}

}
