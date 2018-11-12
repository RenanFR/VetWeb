package com.vetweb.service;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.vetweb.dao.ProprietarioDAO;
import com.vetweb.model.report.Inadimplente;
import com.vetweb.model.report.Report;
import com.vetweb.model.report.ReportType;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Component
public class JasperService {
	
	@Autowired
	private ProprietarioDAO proprietarioDAO;
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/vetweb_database", "postgres", "postgres");
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}

	public void gerarRelatorio(Report report, OutputStream outputStream) throws IOException {
		try {
			Connection connection = getConnection();
			String reportName = report.getType().name();
			Map<String, Object> parameterMap = new HashMap<>();
			if (report.getParameters() != null) {
				parameterMap = report
						.getParameters()
						.stream()
						.collect(Collectors.toMap(param -> param.getKey(), param -> param.getValue()));
			}
			String reportLocation = new ClassPathResource(reportName + ".jrxml").getFile().getAbsolutePath();
			JasperCompileManager.compileReportToFile(reportLocation);
			JasperPrint jasperPrint = null;
			if (report.getType() == ReportType.Inadimplentes) {
				List<Inadimplente> inadimplentes = getInadimplentes();
				jasperPrint = JasperFillManager.fillReport(new ClassPathResource(reportName + ".jasper").getFile().getAbsolutePath(), parameterMap, 
						new JRBeanCollectionDataSource(inadimplentes));
			} else {
				jasperPrint = JasperFillManager.fillReport(new ClassPathResource(reportName + ".jasper").getFile().getAbsolutePath(), parameterMap, connection);
			}
			JRExporter jrExporter = new JRPdfExporter();
			jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			jrExporter.exportReport();
			connection.close();
		} catch (JRException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<Inadimplente> getInadimplentes() {
		List<Inadimplente> inadimplentes = new ArrayList<>();
		proprietarioDAO
			.buscarClientesEmDebito()
			.stream()
			.distinct()
			.forEach(prop -> {
				Inadimplente inadimplente = new Inadimplente();
				inadimplente.setNome(prop.getNome());
				inadimplente.setDebitoTotal(proprietarioDAO.buscarValorPendenteDoCliente(prop).toString());
				Map<String, BigDecimal> debitosPorTipoOcorrenciaECliente = proprietarioDAO.buscarDebitosPorTipoOcorrenciaECliente(prop);
				String debitosText = mapToText(debitosPorTipoOcorrenciaECliente);
				inadimplente.setValoresPorTipoOcorrencia(debitosText);
				inadimplentes.add(inadimplente);
			});
		return inadimplentes;
	}

	private String mapToText(Map<String, BigDecimal> debitosPorTipoOcorrenciaECliente) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Entry<String, BigDecimal> debito : debitosPorTipoOcorrenciaECliente.entrySet()) {
			stringBuilder.append(debito.getKey() + "=" + debito.getValue() + "\n");
		}
		return stringBuilder.toString();
	}

}
