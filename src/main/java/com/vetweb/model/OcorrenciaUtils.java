package com.vetweb.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vetweb.dao.ProprietarioDAO;
import com.vetweb.model.pojo.TipoOcorrenciaProntuario;

@Component
public class OcorrenciaUtils {
	
	@Autowired
	private ProprietarioDAO proprietarioDAO;

	public void autorizaOcorrenciaPorDebito(TipoOcorrenciaProntuario tipoOcorrenciaProntuario, Proprietario proprietario) {
		if (proprietario.isAtivo()) {
			return;
		} else {
			if (tipoOcorrenciaProntuario == TipoOcorrenciaProntuario.ATENDIMENTO) {
				if (proprietarioDAO.buscarClientesEmDebito(TipoOcorrenciaProntuario.ATENDIMENTO).contains(proprietario)) {
					throw new RuntimeException("CLIENTE POSSUI ATENDIMENTOS NÃO PAGOS.");
				}
			} else if (tipoOcorrenciaProntuario == TipoOcorrenciaProntuario.VACINA) {
				if (proprietarioDAO.buscarClientesEmDebito(TipoOcorrenciaProntuario.VACINA).contains(proprietario)) {
					throw new RuntimeException("CLIENTE POSSUI VACINAS NÃO PAGAS.");
				}
			} else if (tipoOcorrenciaProntuario == TipoOcorrenciaProntuario.EXAME) {
				if (proprietarioDAO.buscarClientesEmDebito(TipoOcorrenciaProntuario.EXAME).contains(proprietario)) {
					throw new RuntimeException("CLIENTE POSSUI EXAMES NÃO PAGOS.");
				}
			}
		}
	}	
	
}
