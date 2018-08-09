package com.vetweb.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vetweb.dao.AgendamentoDAO;
import com.vetweb.dao.AnimalDAO;
import com.vetweb.dao.AtendimentoDAO;
import com.vetweb.dao.ProntuarioDAO;
import com.vetweb.dao.ProprietarioDAO;
import com.vetweb.model.Agendamento;
import com.vetweb.model.Animal;
import com.vetweb.model.OcorrenciaAtendimento;
import com.vetweb.model.OcorrenciaVacina;
import com.vetweb.model.Prontuario;
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
	
	@Autowired
	private AnimalDAO animalDAO;
	
	@Autowired
	private AgendamentoDAO agendamentoDAO;
	
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
		List<Agendamento> agendamentos = agendamentoDAO.listarTodos();
		atendimentoDAO
			.listarTodos()
			.stream()
			.filter(atendimento -> atendimento.getData().isBefore(LocalDateTime.now()))
			.filter(atendimento -> aplicarFiltroDeData(dataInicialFiltro, dataFinalFiltro, atendimento))
			.forEach(atendimento -> {
				EventFullCalendar event = new EventFullCalendar();
				event.setId(String.valueOf(atendimento.getOcorrenciaId()));
				event.setTitle(atendimento.getTipoDeAtendimento().getNome());
				event.setStart(DateTimeFormatter.ISO_DATE_TIME.format(atendimento.getData()));
				event.setEnd(DateTimeFormatter.ISO_DATE_TIME.format(atendimento.getData().plus(atendimento.getTipoDeAtendimento().getDuracao())));
				event.setType(atendimento.getTipo().name());
				event.setColor("#ccf5ff");
				events
					.add(event);
			});
		prontuarioDAO
			.buscarTodasOcorrenciasVacina()
			.stream()
			.filter(ocorrenciaVacina -> ocorrenciaVacina.getData().isBefore(LocalDateTime.now()))
			.filter(ocorrenciaVacina -> aplicarFiltroDeData(dataInicialFiltro, dataFinalFiltro, ocorrenciaVacina))
			.forEach(ocorrenciaVacina -> {
				EventFullCalendar event = new EventFullCalendar();
				event.setId(String.valueOf(ocorrenciaVacina.getOcorrenciaId()));
				event.setTitle(ocorrenciaVacina.getDescricao());
				event.setStart(DateTimeFormatter.ISO_DATE_TIME.format(ocorrenciaVacina.getData()));
				event.setType(ocorrenciaVacina.getTipo().name());
				event.setColor("#fff0b3");
				events
					.add(event);
			});
		agendamentos
			.stream()
			.filter(ag -> ag.getDataHoraFinal().isAfter(LocalDateTime.now()))
			.forEach(ag -> {
				EventFullCalendar event = new EventFullCalendar();
				event.setId(String.valueOf(ag.getOcorrencia().getOcorrenciaId()));
				event.setTitle(ag.getOcorrencia().getDescricao());
				event.setStart(DateTimeFormatter.ISO_DATE_TIME.format(ag.getDataHoraInicial()));
				event.setEnd(DateTimeFormatter.ISO_DATE_TIME.format(ag.getDataHoraFinal()));
				event.setType(ag.getOcorrencia().getTipo().name());
				event.setColor("#ccffcc");
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
	
	@PostMapping("/ocorrencia")
	public ModelAndView adicionarOcorrencia(@RequestParam("slcProprietarios") String codigoCliente, 
			@RequestParam("slcAnimal") String animalSelecionado, 
			@RequestParam("tipoOcorrencia") String tipoDeOcorrencia, 
			@RequestParam(value = "slcVacina", required = false) String vacinaSelecionada,
			@RequestParam(value = "slcAtendimento", required = false) String atendimentoSelecionado, 
			@RequestParam("dataHoraInicial") String dataEHoraInicial, 
			@RequestParam("dataHoraFinal") String dataEHoraFinal) {
		Animal animal = animalDAO.buscarPorId(Long.parseLong(animalSelecionado));
		Prontuario prontuario = animal.getProntuario();
		LocalDateTime dataHoraInicio = LocalDateTime.parse(dataEHoraInicial, DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime dataHoraFim = LocalDateTime.parse(dataEHoraFinal, DateTimeFormatter.ISO_DATE_TIME);
		TipoOcorrenciaProntuario tipoOcorrencia = TipoOcorrenciaProntuario.valueOf(tipoDeOcorrencia);
		Agendamento agendamento = new Agendamento();
		if (tipoDeOcorrencia.equals("VACINA")) {
			OcorrenciaVacina vacina = new OcorrenciaVacina();
			vacina.setProntuario(prontuario);
			vacina.setVacina(prontuarioDAO.buscarVacinaPorId(Long.parseLong(vacinaSelecionada)));
			vacina.setData(dataHoraInicio);
			vacina.setTipo(tipoOcorrencia);
			prontuarioDAO.salvarOcorrenciaVacina(vacina);
			prontuario.getVacinas().add(vacina);
			agendamento.setOcorrencia(vacina);
		} else if (tipoDeOcorrencia.equals("ATENDIMENTO")) {
			OcorrenciaAtendimento atendimento = new OcorrenciaAtendimento();
			atendimento.setTipoDeAtendimento(atendimentoDAO.buscarTipoDeAtendimentoPorId(Long.parseLong(atendimentoSelecionado)));
			atendimento.setProntuario(prontuario);
			atendimento.setData(dataHoraInicio);
			atendimento.setTipo(tipoOcorrencia);
			prontuarioDAO.salvarAtendimento(atendimento);
			prontuario.getAtendimentos().add(atendimento);
			agendamento.setOcorrencia(atendimento);
		}
		prontuarioDAO.salvar(prontuario);
		agendamento.setDataHoraInicial(dataHoraInicio);
		agendamento.setDataHoraFinal(dataHoraFim);
		agendamento.setTipo(tipoOcorrencia);
		agendamentoDAO.salvar(agendamento);
		ModelAndView modelAndView = new ModelAndView("redirect:/prontuario/prontuarioDoAnimal/" + prontuario.getAnimal().getAnimalId());
		return modelAndView;
	}
	
}