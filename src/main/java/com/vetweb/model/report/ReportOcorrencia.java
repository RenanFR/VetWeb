package com.vetweb.model.report;

import java.util.Arrays;
import java.util.Date;

public class ReportOcorrencia extends Report {

	public ReportOcorrencia(Date dataIni, Date dataFim) {
		super(ReportType.Ocorrencia, Arrays.asList(new ReportParameter("Data_Ini", ParameterType.DATE, dataIni), 
				new ReportParameter("Data_Fim", ParameterType.DATE, dataFim)));
	}

}
