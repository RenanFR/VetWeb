package com.vetweb.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class JasperService {
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/vetweb_database", "postgres", "postgres");
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}
	
	public static void main(String[] args) {
		try {
			Connection connection = getConnection();
			String path = new ClassPathResource("Ocorrencia.jrxml").getFile().getAbsolutePath();
			JasperCompileManager.compileReportToFile("/home/renanfr/git/vetweb/src/main/resources/Ocorrencia.jrxml");
			Map<String, Object> map = new HashMap<>();
			JasperPrint jasperPrint = JasperFillManager.fillReport("Ocorrencia.jasper", map, connection);;
			JRExporter jrExporter = new JRPdfExporter();
			jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, "Ocorrencia.pdf");
			connection.close();
		} catch (JRException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
