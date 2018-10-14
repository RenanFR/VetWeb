package com.vetweb.controller;
//@author renan.rodrigues@metasix.com.br

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vetweb.dao.AgendamentoDAO;
import com.vetweb.dao.AnimalDAO;
import com.vetweb.dao.AtendimentoDAO;
import com.vetweb.dao.ExameDAO;
import com.vetweb.dao.ProntuarioDAO;
import com.vetweb.dao.VacinaDAO;
import com.vetweb.jms.JMSNotificacaoOcorrenciaCliente;
import com.vetweb.model.Agendamento;
import com.vetweb.model.Animal;
import com.vetweb.model.Exame;
import com.vetweb.model.OcorrenciaAtendimento;
import com.vetweb.model.OcorrenciaExame;
import com.vetweb.model.OcorrenciaPatologia;
import com.vetweb.model.OcorrenciaUtils;
import com.vetweb.model.OcorrenciaVacina;
import com.vetweb.model.Patologia;
import com.vetweb.model.Prontuario;
import com.vetweb.model.Proprietario;
import com.vetweb.model.TipoDeAtendimento;
import com.vetweb.model.Vacina;
import com.vetweb.model.error.DebitoOcorrenciaException;
import com.vetweb.model.pojo.OcorrenciaProntuario;
import com.vetweb.model.pojo.TipoOcorrenciaProntuario;

@Controller
@Transactional
@RequestMapping("/prontuario")
public class ProntuarioController {
	
    @Autowired
    private AtendimentoDAO atendimentoDAO;
    
    @Autowired
    private ProntuarioDAO prontuarioDAO;
    
    @Autowired
    private AnimalDAO animalDAO;
    
    @Autowired
    private VacinaDAO vacinaDAO;
    
    @Autowired
    private ExameDAO exameDAO;
    
    @Autowired
    private AgendamentoDAO agendamentoDAO;
    
    @Autowired
    private JMSNotificacaoOcorrenciaCliente jmsNotificaOcorrenciaCliente;
    
    @Autowired
    private OcorrenciaUtils ocorrenciaUtils;
    
    public static String modelDML = null;
    
    private static final Logger LOGGER = Logger.getLogger(ProntuarioController.class);
    
    @RequestMapping(value = "/cadastroTipoAtendimento", method = RequestMethod.GET)
    public ModelAndView formTipoAtendimento(TipoDeAtendimento tipoDeAtendimento) {
        ModelAndView modelAndView = new ModelAndView("prontuario/cadastroTipoAtendimento");
        Set<Duration> duracoesValidas = Stream.of(Duration.ofMinutes(30), Duration.ofHours(1), Duration.ofHours(3), Duration.ofHours(5))
                .collect(Collectors.toSet());
        Set<Period> frequenciasValidas = Stream.of(Period.ofDays(1), Period.ofDays(15), Period.ofMonths(1), Period.ofYears(1), Period.ofWeeks(2))
                .collect(Collectors.toSet());
        modelAndView.addObject("duracoesValidas", duracoesValidas);
        modelAndView.addObject("frequenciasValidas", frequenciasValidas);
        return modelAndView;
    }
    
    @RequestMapping(value = "/cadastroVacina", method = RequestMethod.GET)
    public ModelAndView formVacina(Vacina vacina) {
        ModelAndView modelAndView = new ModelAndView("prontuario/cadastroVacina");
        return modelAndView;
    }
    
    @RequestMapping(value = "/addTipoAtendimento", method = RequestMethod.POST)
    public ModelAndView cadTipoAtendimento(@ModelAttribute("tipoDeAtendimento") TipoDeAtendimento tipoDeAtendimento) {
        ModelAndView modelAndView = new ModelAndView("redirect:tiposDeAtendimento");
        atendimentoDAO.salvarTipoDeAtendimento(tipoDeAtendimento);
        return modelAndView;
    }
    
    @RequestMapping(value = "/addVacina", method = RequestMethod.POST)
    public ModelAndView cadVacina(@ModelAttribute("vacina") Vacina vacina) {
        ModelAndView modelAndView = new ModelAndView("redirect:vacinas");
        vacinaDAO.salvar(vacina);
        return modelAndView;
    }
    
    @RequestMapping(value = "/tiposDeAtendimento", method = RequestMethod.GET)
    public ModelAndView listarTiposDeAtendimento(){
        ModelAndView modelAndView = new ModelAndView("prontuario/tiposDeAtendimento");
        modelAndView.addObject("tiposDeAtendimento", atendimentoDAO.buscarTiposDeAtendimento());
        return modelAndView;
    }
    
    @RequestMapping(value = "/vacinas", method = RequestMethod.GET)
    public ModelAndView listarVacinas(){
        ModelAndView modelAndView = new ModelAndView("prontuario/vacinas");
        modelAndView.addObject("vacinas", vacinaDAO.listarTodos());
        return modelAndView;
    }
    
    @RequestMapping(value = "/atualizarTipoDeAtendimento/{tipoDeAtendimentoId}", method = RequestMethod.GET)
    public ModelAndView atualizarTipoDeAtendimento(@PathVariable("tipoDeAtendimentoId")Long tipoDeAtendimentoId) {
    	ModelAndView modelAndView = new ModelAndView("prontuario/cadastroTipoAtendimento");
    	Set<Duration> duracoesValidas = Stream.of(Duration.ofMinutes(30), Duration.ofHours(1), Duration.ofHours(3), Duration.ofHours(5))
    			.collect(Collectors.toSet());
    	Set<Period> frequenciasValidas = Stream.of(Period.ofDays(15), Period.ofMonths(1), Period.ofYears(1), Period.ofWeeks(2))
    			.collect(Collectors.toSet());
    	modelAndView.addObject("duracoesValidas", duracoesValidas);
    	modelAndView.addObject("frequenciasValidas", frequenciasValidas);        
    	modelAndView.addObject("tipoDeAtendimento", atendimentoDAO.buscarTipoDeAtendimentoPorId(tipoDeAtendimentoId));
    	return modelAndView;
    }
    
    @RequestMapping(value = "/atualizarVacina/{vacinaId}", method = RequestMethod.GET)
    public ModelAndView atualizarVacina(@PathVariable("vacinaId")Long vacinaId) {
        ModelAndView modelAndView = new ModelAndView("prontuario/cadastroVacina");
        modelAndView.addObject("vacina", vacinaDAO.buscarPorId(vacinaId));
        return modelAndView;
    }
    
    @RequestMapping(value = "/removerTipoDeAtendimento/{tipoDeAtendimentoId}", method = RequestMethod.GET)
    public ModelAndView delTipoDeAtendimento(@PathVariable("tipoDeAtendimentoId")Long tipoDeAtendimentoId) {
    	ModelAndView modelAndView = new ModelAndView("redirect:/prontuario/tiposDeAtendimento");
    	atendimentoDAO.removerTipoDeAtendimento(atendimentoDAO.buscarTipoDeAtendimentoPorId(tipoDeAtendimentoId));
    	return modelAndView;
    }
    
    @RequestMapping(value = "/removerVacina/{vacinaId}", method = RequestMethod.GET)
    public ModelAndView delVacina(@PathVariable("vacinaId")Long vacinaId) {
    	modelDML = "Vacina";
        ModelAndView modelAndView = new ModelAndView("redirect:/prontuario/vacinas");
        vacinaDAO.remover(vacinaDAO.buscarPorId(vacinaId));
        return modelAndView;
    }
    
    @RequestMapping(value = "/gerarProntuario", method = RequestMethod.POST)
    public ModelAndView criarProntuarioDoAnimal(@RequestParam("animalId") Long animalId) {
		Animal animal = animalDAO.buscarPorId(animalId);
        LOGGER.info(("SENDO CRIADO PRONTUÁRIO DO ANIMAL " + animal).toUpperCase());
        Prontuario prontuario = new Prontuario(animal);
        prontuarioDAO.salvar(prontuario);
        animal.setProntuario(prontuario);
        ModelAndView modelAndView = new ModelAndView("redirect:/prontuario/prontuarioDoAnimal/" + animal.getAnimalId());
        return modelAndView;
    }
    
    @Cacheable(cacheNames = "prontuario")
    @RequestMapping(value = "/prontuarioDoAnimal/{animalId}", method = RequestMethod.GET)
    public ModelAndView prontuarioDoAnimal(@PathVariable("animalId") final Long animalId, @ModelAttribute("atendimento") OcorrenciaAtendimento atendimento,
    		@ModelAttribute("prontuarioPatologia") Patologia patologia, @ModelAttribute("prontuarioVacina") Vacina vacina) {
        ModelAndView modelAndView = new ModelAndView("prontuario/prontuario");
        Prontuario prontuario = prontuarioDAO.buscarProntuarioPorAnimal(animalId);
		modelAndView.addObject("prontuario", prontuario);
		modelAndView.addObject("tiposDeAtendimento", atendimentoDAO.buscarTiposDeAtendimento());
		modelAndView.addObject("vacinas", prontuarioDAO.buscarVacinas().stream()
	    		.map(vac -> vac.getNome()).collect(Collectors.toList()));
		modelAndView.addObject("patologias", animalDAO.buscarPatologias().stream()
	    		.map(pat -> pat.getNome()).collect(Collectors.toList()));
		modelAndView.addObject("exames", exameDAO.listarTodos().stream()
    			.map(exame -> exame.getDescricao()).collect(Collectors.toList()));
		List<OcorrenciaProntuario> elementosHistorico = adicionaHistoricoAoProntuario(prontuario);
		modelAndView.addObject("historico", elementosHistorico);
    	Proprietario proprietario = prontuario.getAnimal().getProprietario();
    	addDebitosClientePagina(modelAndView, proprietario);
        return modelAndView;
    }

	private void addDebitosClientePagina(ModelAndView modelAndView, Proprietario proprietario) {
		for (TipoOcorrenciaProntuario tipoOcorrenciaProntuario : TipoOcorrenciaProntuario.values() ) {
			try {
				ocorrenciaUtils.autorizaOcorrenciaPorDebito(tipoOcorrenciaProntuario, proprietario);
			} catch (DebitoOcorrenciaException debitoOcorrenciaException) {
				String contemDebitoTipo = "possuiDebito" + StringUtils.capitalize(debitoOcorrenciaException.getTipoOcorrenciaProntuario().toString().toLowerCase());
				modelAndView.addObject(contemDebitoTipo, true);
			}
		}
	}

	private List<OcorrenciaProntuario> adicionaHistoricoAoProntuario(Prontuario prontuario) {
		List<OcorrenciaProntuario> elementosHistorico = new ArrayList<>();
		prontuario.getAtendimentos().forEach(at -> elementosHistorico.add(at));
		prontuario.getPatologias().forEach(pat -> elementosHistorico.add(pat));
		prontuario.getVacinas().forEach(vac -> elementosHistorico.add(vac));
		prontuario.getExames().forEach(ex -> elementosHistorico.add(ex));
		return elementosHistorico;
	}
    
    @RequestMapping(value = "/adicionarAtendimento", method = RequestMethod.POST)
    public ModelAndView addAtendimento(@ModelAttribute("atendimento") OcorrenciaAtendimento atendimento,
    		@RequestParam("prontuarioId") final Long prontuarioId, 
    		RedirectAttributes redirectAttributes) {
    	LOGGER.debug("Inserindo atendimento " + atendimento.getTipoDeAtendimento().getNome() + " no prontuário " + prontuarioId);
    	Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
    	ModelAndView modelAndView = new ModelAndView("redirect:prontuarioDoAnimal/" + prontuario.getAnimal().getAnimalId());
    	atendimento.setTipo(TipoOcorrenciaProntuario.ATENDIMENTO);
    	atendimento.setProntuario(prontuario);
        prontuarioDAO.salvarAtendimento(atendimento);
        notificaCliente(atendimento);
        agendarOcorrencia(atendimento);
		return modelAndView;
    }

	private void notificaCliente(OcorrenciaProntuario elementoProntuario) {
		jmsNotificaOcorrenciaCliente.sendNotification("notifica_ocorrencia_cliente", elementoProntuario);
	}
    
    @RequestMapping(value="/adicionarPatologia", method=RequestMethod.POST)
    public ModelAndView addPatologia(@RequestParam("prontuarioId") final Long prontuarioId,
    		@RequestParam("patologia") final String patologiaStr, 
    		@RequestParam("prontuarioPatologiaId") final Long prontuarioPatologiaId,
    	@RequestParam("data") final String inclusaoPatologia) {
    	LOGGER.debug(("Inserindo patologia " + patologiaStr + " no prontuário " + prontuarioId).toUpperCase());
    	OcorrenciaPatologia prontuarioPatologia = new OcorrenciaPatologia();
    	prontuarioPatologia.setTipo(TipoOcorrenciaProntuario.PATOLOGIA);
    	Patologia pat = animalDAO.buscarPatologiaPorDescricao(patologiaStr);
		animalDAO.salvarPatologia(pat);
		if (prontuarioPatologiaId != null) {
			prontuarioPatologia = prontuarioDAO.buscarOcorrenciaPatologia(prontuarioPatologiaId);
		}
		prontuarioPatologia.setPatologia(pat);
		Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
		ModelAndView modelAndView = new ModelAndView("redirect:prontuarioDoAnimal/" + prontuario.getAnimal().getAnimalId());
		prontuarioPatologia.setProntuario(prontuario);
		prontuarioPatologia.setData(LocalDateTime.parse(inclusaoPatologia, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
		prontuarioDAO.salvarOcorrenciaPatologia(prontuarioPatologia);
    	notificaCliente(prontuarioPatologia);
		return modelAndView;
    }
    
    @RequestMapping(value="/adicionarExame", method=RequestMethod.POST)
    public ModelAndView addExame(@RequestParam("prontuarioId") final Long prontuarioId,
    		@RequestParam("exame") final String exameDescricao, 
    		@RequestParam("ocorrenciaExameId") final Long ocorrenciaId,
    		@RequestParam("data") final String inclusaoExame,
    		RedirectAttributes redirectAttributes) {
    	Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
    	ModelAndView modelAndView = new ModelAndView("redirect:prontuarioDoAnimal/" + prontuario.getAnimal().getAnimalId());
    	OcorrenciaExame ocorrenciaExame = new OcorrenciaExame();
    	ocorrenciaExame.setOcorrenciaId(ocorrenciaId);
    	ocorrenciaExame.setProntuario(prontuario);
    	ocorrenciaExame.setData(LocalDateTime.parse(inclusaoExame, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
    	ocorrenciaExame.setPago(false);
    	ocorrenciaExame.setTipo(TipoOcorrenciaProntuario.EXAME);
    	Exame exame = exameDAO.buscarPorDescricao(exameDescricao);
		ocorrenciaExame.setExame(exame);
		agendarOcorrencia(ocorrenciaExame);
		prontuarioDAO.salvarOcorrenciaExame(ocorrenciaExame);
		notificaCliente(ocorrenciaExame);
		return modelAndView;
    }

	private void agendarOcorrencia(OcorrenciaProntuario ocorrenciaProntuario) {
		if (ocorrenciaProntuario.getData().isAfter(LocalDateTime.now())) {
			Agendamento agendamento = new Agendamento();
			agendamento.setOcorrencia(ocorrenciaProntuario);
			agendamento.setTipo(TipoOcorrenciaProntuario.EXAME);
			agendamento.setDataHoraInicial(ocorrenciaProntuario.getData());
			agendamento.setDataHoraFinal(ocorrenciaProntuario.getData().plusHours(1));
			agendamentoDAO.salvar(agendamento);
		}
	}
    
    @RequestMapping(value="/adicionarVacina", method=RequestMethod.POST)
    public ModelAndView addVacina(@RequestParam("prontuarioId") final Long prontuarioId,
    		@RequestParam("vacina") final String vacinaStr,
    		@RequestParam("prontuarioVacinaId") final Long prontuarioVacinaId,
    		@RequestParam("data") final String inclusaoVacina,
    		RedirectAttributes redirectAttributes) {
    	LOGGER.info(("Inserindo vacina " + vacinaStr + " no prontuário " + prontuarioId).toUpperCase());
    	Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
    	ModelAndView modelAndView = new ModelAndView("redirect:prontuarioDoAnimal/" + prontuarioDAO.buscarPorId(prontuarioId).getAnimal().getAnimalId());
    	OcorrenciaVacina prontuarioVacina = new OcorrenciaVacina();
    	prontuarioVacina.setTipo(TipoOcorrenciaProntuario.VACINA);
    	Vacina vacina = vacinaDAO.buscarPorNome(vacinaStr);
		vacinaDAO.salvar(vacina);
		if (prontuarioVacinaId != null)
			prontuarioVacina = prontuarioDAO.buscarOcorrenciaVacina(prontuarioVacinaId);
		prontuarioVacina.setVacina(vacina);
		prontuarioVacina.setProntuario(prontuario);
		prontuarioVacina.setData(LocalDateTime.parse(inclusaoVacina, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
    	prontuarioDAO.salvarOcorrenciaVacina(prontuarioVacina);
    	agendarOcorrencia(prontuarioVacina);
    	notificaCliente(prontuarioVacina);
		return modelAndView;
    }
    
    @RequestMapping(value = "/removerAtendimentoDoProntuario/{prontuarioId}/{atendimentoId}", method = RequestMethod.GET)
    public ModelAndView removerAtendimentoDoProntuario(@PathVariable("prontuarioId")Long prontuarioId, @PathVariable("atendimentoId")Long atendimentoId,
    		@ModelAttribute("atendimento") OcorrenciaAtendimento atendimento,
    		@ModelAttribute("prontuarioPatologia") Patologia patologia,
    		@ModelAttribute("prontuarioVacina") Vacina vacina) {
    	Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
    	ModelAndView modelAndView = new ModelAndView("redirect:/prontuario/prontuarioDoAnimal/" + prontuario.getAnimal().getAnimalId());
		modelAndView.addObject("prontuario", prontuario);
    	prontuarioDAO.removerAtendimento(atendimentoDAO.buscarPorId(atendimentoId));
    	adicionarListasAoProntuario(modelAndView);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/removerVacinaDoProntuario/{prontuarioId}/{vacinaId}", method = RequestMethod.GET)
    public ModelAndView removerVacinaDoProntuario(@PathVariable("prontuarioId")Long prontuarioId, @PathVariable("vacinaId")Long vacinaId,
    		@RequestParam("inclusaoOcorrenciaVacina") String inclusaoVacina,
    		@ModelAttribute("atendimento") OcorrenciaAtendimento atendimento,
    		@ModelAttribute("prontuarioPatologia") Patologia patologia,
    		@ModelAttribute("prontuarioVacina") Vacina vacina) {
    	Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
    	ModelAndView modelAndView = new ModelAndView("redirect:/prontuario/prontuarioDoAnimal/" + prontuario.getAnimal().getAnimalId());
		modelAndView.addObject("prontuario", prontuario);
    	prontuarioDAO.removerOcorrenciaVacina(prontuarioDAO.buscarOcorrenciaVacina(vacinaId));
    	adicionarListasAoProntuario(modelAndView);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/removerPatologiaDoProntuario/{prontuarioId}/{patologiaId}", method = RequestMethod.GET)
    public ModelAndView removerPatologiaDoProntuario(@PathVariable("prontuarioId")Long prontuarioId, @PathVariable("patologiaId")Long patologiaId,
    		@ModelAttribute("atendimento") OcorrenciaAtendimento atendimento,
    		@ModelAttribute("prontuarioPatologia") Patologia patologia,
    		@ModelAttribute("prontuarioVacina") Vacina vacina) {
    	Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
    	ModelAndView modelAndView = new ModelAndView("redirect:/prontuario/prontuarioDoAnimal/" + prontuario.getAnimal().getAnimalId());
		modelAndView.addObject("prontuario", prontuario);
    	prontuarioDAO.removerOcorrenciaPatologia(prontuarioDAO.buscarOcorrenciaPatologia(patologiaId));
    	adicionarListasAoProntuario(modelAndView);
		return modelAndView;
    }
    
    @RequestMapping(value = "/removerExameDoProntuario/{prontuarioId}/{exameId}", method = RequestMethod.GET)
    public ModelAndView removerExameDoProntuario(@PathVariable("prontuarioId")Long prontuarioId, @PathVariable("exameId")Long exameId,
    		@ModelAttribute("atendimento") OcorrenciaAtendimento atendimento,
    		@ModelAttribute("prontuarioPatologia") Patologia patologia,
    		@ModelAttribute("prontuarioVacina") Vacina vacina) {
    	Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
    	ModelAndView modelAndView = new ModelAndView("redirect:/prontuario/prontuarioDoAnimal/" + prontuario.getAnimal().getAnimalId());
    	modelAndView.addObject("prontuario", prontuario);
    	prontuarioDAO.removerOcorrenciaExame(prontuarioDAO.buscarOcorrenciaExame(exameId));
    	adicionarListasAoProntuario(modelAndView);
    	return modelAndView;
    }
    
    @SuppressWarnings("rawtypes")
	private void adicionarListasAoProntuario(ModelAndView viewProntuario) {
    	Map<String, List> listasProntuario = new HashMap<>();
    	listasProntuario.put("tiposDeAtendimento", atendimentoDAO.buscarTiposDeAtendimento());
    	LOGGER.info("ADICIONANDO LISTA DE SERVIÇOS P/ INCLUSÃO DE ATENDIMENTOS NO PRONTUÁRIO.");
    	listasProntuario.put("vacinas", prontuarioDAO.buscarVacinas().stream()
	    		.map(v -> v.getNome()).collect(Collectors.toList()));
    	LOGGER.info("ADICIONANDO LISTA DE VACINAS DISPONÍVEIS PARA USO NO PRONTUÁRIO. ");
    	listasProntuario.put("patologias", animalDAO.buscarPatologias().stream()
	    		.map(pat -> pat.getNome()).collect(Collectors.toList()));
    	LOGGER.info("ADICIONANDO POSSÍVEIS PATOLOGIAS QUE PODEM SER ANEXADAS NO HISTÓRICO DO ANIMAL NO PRONTUÁRIO. ");
    	listasProntuario.put("exames", exameDAO.listarTodos().stream()
    			.map(exame -> exame.getDescricao()).collect(Collectors.toList()));
    	viewProntuario.addAllObjects(listasProntuario);
    }
    
}
