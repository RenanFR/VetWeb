package com.vetweb.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vetweb.dao.AtendimentoDAO;
import com.vetweb.dao.ProntuarioDAO;
import com.vetweb.dao.ProprietarioDAO;
import com.vetweb.model.OcorrenciaAtendimento;
import com.vetweb.model.OcorrenciaVacina;
import com.vetweb.model.pojo.EventFullCalendar;
import com.vetweb.model.pojo.OcorrenciaProntuario;
import com.vetweb.model.pojo.TipoOcorrenciaProntuario;

@Controller
@Transactional
@RequestMapping("/agendamento")
public class AgendamentoController {
	
	private static final Logger LOGGER = Logger.getLogger(AgendamentoController.class);
	
	@Autowired
	private AtendimentoDAO atendimentoDAO;
	
	@Autowired
	private ProntuarioDAO prontuarioDAO;
	
	@Autowired
	private ProprietarioDAO proprietarioDAO;
	
	@GetMapping
	public ModelAndView agenda() {
		ModelAndView modelAndView = new ModelAndView("agendamento/agendamento");
		modelAndView.addObject("todosOsClientes", proprietarioDAO.listarTodos());
		modelAndView.addObject("tiposDeAtendimento", atendimentoDAO.buscarTiposDeAtendimento());
		modelAndView.addObject("todasAsVacinas", prontuarioDAO.buscarVacinas());
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/eventos")
	public List<EventFullCalendar> todosOsEventos(@RequestParam(value = "start", required = false) String start, @RequestParam(value = "end", required = false) String end) {
		LOGGER.debug("Recebendo data inicial " + start + " e final " + end + " para exibição dos eventos");
		LocalDate dataInicialFiltro = LocalDate.parse(start, DateTimeFormatter.ISO_DATE);
		LocalDate dataFinalFiltro = LocalDate.parse(end, DateTimeFormatter.ISO_DATE);
		List<EventFullCalendar> events = new ArrayList<>();
		atendimentoDAO
			.listarTodos()
			.stream()
			.filter(atendimento -> aplicarFiltroDeData(dataInicialFiltro, dataFinalFiltro, atendimento))
			.forEach(atendimento -> {
				EventFullCalendar event = new EventFullCalendar();
				event.setId(String.valueOf(atendimento.getOcorrenciaId()));
				event.setTitle(atendimento.getTipoDeAtendimento().getNome());
				event.setStart(DateTimeFormatter.ISO_DATE_TIME.format(atendimento.getDataAtendimento()));
				event.setEnd(DateTimeFormatter.ISO_DATE_TIME.format(atendimento.getDataAtendimento().plus(atendimento.getTipoDeAtendimento().getDuracao())));
				event.setType(atendimento.getTipo().name());
				events
					.add(event);
			});
		prontuarioDAO
			.buscarTodasOcorrenciasVacina()
			.stream()
			.filter(ocorrenciaVacina -> aplicarFiltroDeData(dataInicialFiltro, dataFinalFiltro, ocorrenciaVacina))
			.forEach(ocorrenciaVacina -> {
				EventFullCalendar event = new EventFullCalendar();
				event.setId(String.valueOf(ocorrenciaVacina.getOcorrenciaId()));
				event.setTitle(ocorrenciaVacina.getDescricao());
				event.setStart(DateTimeFormatter.ISO_DATE_TIME.format(ocorrenciaVacina.getData()));
				event.setType(ocorrenciaVacina.getTipo().name());
				events
				.add(event);
			});
		return events;
	}

	private boolean aplicarFiltroDeData(LocalDate dataInicialFiltro, LocalDate dataFinalFiltro,
			OcorrenciaProntuario ocorrenciaProntuario) {
		LocalDate dataDoAtendimento = LocalDate
				.of(ocorrenciaProntuario.getData().getYear(), 
						ocorrenciaProntuario.getData().getMonthValue(), 
						ocorrenciaProntuario.getData().getDayOfMonth());
		return (dataDoAtendimento.isEqual(dataInicialFiltro) || dataDoAtendimento.isAfter(dataInicialFiltro))
				&&
				(dataDoAtendimento.isEqual(dataFinalFiltro) || dataDoAtendimento.isBefore(dataFinalFiltro));
	}
	
	@GetMapping("/ocorrencia/{type}/{id}")
	public ModelAndView buscarOcorrencia(@PathVariable("type") String tpOcorrencia, @PathVariable("id") Long idOcorrencia) {
		TipoOcorrenciaProntuario tipoOcorrencia = TipoOcorrenciaProntuario.valueOf(tpOcorrencia);
		OcorrenciaProntuario ocorrencia = null;
		switch (tipoOcorrencia) {
			case ATENDIMENTO: {
				ocorrencia = atendimentoDAO.buscarPorId(idOcorrencia);
				ocorrencia = (OcorrenciaAtendimento)ocorrencia;
				break;
			}
			case VACINA: {
				ocorrencia = prontuarioDAO.buscarOcorrenciaVacina(idOcorrencia);
				ocorrencia = (OcorrenciaVacina)ocorrencia;
				break;
			}
			case PATOLOGIA: {
				throw new RuntimeException("Patologia não é um tipo de evento suportado p/ agendamento");
			}
		}
		ModelAndView modelAndView = new ModelAndView("forward:/prontuario/prontuarioDoAnimal/" + ocorrencia.getProntuario().getAnimal().getAnimalId());
		return modelAndView;
	}
	
	@RequestMapping(value = "/ocorrencia", method = RequestMethod.POST)
	public ModelAndView adicionarOcorrencia() {
		return null;
	}
	
}
