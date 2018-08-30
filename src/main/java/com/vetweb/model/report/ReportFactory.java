package com.vetweb.model.report;

import java.util.Date;

public class ReportFactory {
	
	public static Report createReport(ReportType type, Object... values) {
		Report report = null;
		if (type == ReportType.Ocorrencia) {
			Date dataIni = (Date)values[0];
			Date dataFim = (Date)values[1];
			report = new ReportOcorrencia(dataIni, dataFim);
		}
		return report;
	}

}
