package com.vetweb.model;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vetweb.dao.AnimalDAO;
import com.vetweb.dao.AtendimentoDAO;
import com.vetweb.dao.ExameDAO;
import com.vetweb.dao.ProntuarioDAO;
import com.vetweb.model.pojo.OcorrenciaProntuario;
import com.vetweb.model.pojo.TipoOcorrenciaProntuario;

@Component
public class OcorrenciaFactory {
	
	private AtendimentoDAO daoAtendimento;
	
	private ProntuarioDAO daoProntuario;
	
	private AnimalDAO daoAnimal;
	
	private ExameDAO daoExame;
	
	private OcorrenciaUtils ocorrenciaUtils;
	
	@Autowired
	private OcorrenciaFactory(AtendimentoDAO atendimentoDAO, ProntuarioDAO prontuarioDAO, AnimalDAO animalDAO, ExameDAO exameDAO, OcorrenciaUtils ocorrenciaUtils) {
		this.daoAtendimento = atendimentoDAO;
		this.daoProntuario = prontuarioDAO;
		this.daoAnimal = animalDAO;
		this.daoExame = exameDAO;
		this.ocorrenciaUtils = ocorrenciaUtils;
	}
	
	public OcorrenciaProntuario getOcorrencia(String tipo, Long idOcorrencia) {
		OcorrenciaProntuario ocorrencia = null;
		TipoOcorrenciaProntuario tipoOcorrencia = TipoOcorrenciaProntuario.valueOf(tipo);
		switch (tipoOcorrencia) {
			case ATENDIMENTO: {
				ocorrencia = daoAtendimento.buscarPorId(idOcorrencia);
				ocorrencia = (OcorrenciaAtendimento)ocorrencia;
				break;
			}
			case VACINA: {
				ocorrencia = daoProntuario.buscarOcorrenciaVacina(idOcorrencia);
				ocorrencia = (OcorrenciaVacina)ocorrencia;
				break;
			}
			case EXAME: {
				ocorrencia = daoProntuario.buscarOcorrenciaExame(idOcorrencia);
				ocorrencia = (OcorrenciaExame)ocorrencia;
				break;
			}
			case PATOLOGIA: {
				throw new RuntimeException("Patologia não é um tipo de evento suportado p/ agendamento");
			}
		}
		return ocorrencia;
	}

	public OcorrenciaProntuario criarOcorrencia(String opcaoDescritivo, LocalDateTime dataHoraInicio, TipoOcorrenciaProntuario tipoOcorrencia, Prontuario prontuario) {
		OcorrenciaProntuario ocorrenciaProntuario = null;
		switch (tipoOcorrencia) {
			case ATENDIMENTO: {
				TipoDeAtendimento tipoDeAtendimento = daoAtendimento.buscarTipoDeAtendimentoPorId(Long.parseLong(opcaoDescritivo));
				ocorrenciaProntuario = new OcorrenciaAtendimento(tipoDeAtendimento, tipoDeAtendimento.getModeloAtendimento());
				OcorrenciaAtendimento ocorrenciaAtendimento = (OcorrenciaAtendimento)ocorrenciaProntuario;
				ocorrenciaAtendimento.setProntuario(prontuario);
				daoProntuario.salvarAtendimento(ocorrenciaAtendimento);
				prontuario.getAtendimentos().add(ocorrenciaAtendimento);
				break;
			}
			case PATOLOGIA: {
				Patologia patologia = daoAnimal.buscarPatologiaPorDescricao(opcaoDescritivo);
				ocorrenciaProntuario = new OcorrenciaPatologia(patologia);
				OcorrenciaPatologia ocorrenciaPatologia = (OcorrenciaPatologia) ocorrenciaProntuario;
				ocorrenciaPatologia.setProntuario(prontuario);
				daoProntuario.salvarOcorrenciaPatologia(ocorrenciaPatologia);
				prontuario.getPatologias().add(ocorrenciaPatologia);
				break;
			}
			case VACINA: {
				Vacina vacina = daoProntuario.buscarVacinaPorId(Long.parseLong(opcaoDescritivo));
				ocorrenciaProntuario = new OcorrenciaVacina(vacina);
				OcorrenciaVacina ocorrenciaVacina = (OcorrenciaVacina)ocorrenciaProntuario;
				ocorrenciaVacina.setProntuario(prontuario);
				daoProntuario.salvarOcorrenciaVacina(ocorrenciaVacina);
				prontuario.getVacinas().add(ocorrenciaVacina);
				break;
			}
			case EXAME: {
				Exame exame = daoExame.buscarPorDescricao(opcaoDescritivo);
				ocorrenciaProntuario = new OcorrenciaExame(exame);
				OcorrenciaExame ocorrenciaExame = (OcorrenciaExame)ocorrenciaProntuario;
				ocorrenciaExame.setProntuario(prontuario);
				daoProntuario.salvarOcorrenciaExame(ocorrenciaExame);
				prontuario.getExames().add(ocorrenciaExame);
				break;
			}
		}
		ocorrenciaProntuario.setData(dataHoraInicio);
		ocorrenciaProntuario.setTipo(tipoOcorrencia);
		Proprietario proprietario = ocorrenciaProntuario.getProntuario().getAnimal().getProprietario();
		ocorrenciaUtils.autorizaOcorrenciaPorDebito(ocorrenciaProntuario.getTipo(), proprietario);
		daoProntuario.salvar(prontuario);
		return ocorrenciaProntuario;
	}

}
