package com.vetweb.model.report;

import java.util.List;

public abstract class Report {
	
	private ReportType type;
	
	private List<ReportParameter> parameters;

	public Report(ReportType type, List<ReportParameter> parameters) {
		this.type = type;
		this.parameters = parameters;
	}

	public ReportType getType() {
		return type;
	}

	public void setType(ReportType type) {
		this.type = type;
	}

	public List<ReportParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<ReportParameter> parameters) {
		this.parameters = parameters;
	}
	

}
