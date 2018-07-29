package com.vetweb.controller;
//@author renan.rodrigues@metasix.com.br

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vetweb.dao.AnimalDAO;
import com.vetweb.dao.AtendimentoDAO;
import com.vetweb.dao.ProntuarioDAO;
import com.vetweb.dao.VacinaDAO;
import com.vetweb.model.Animal;
import com.vetweb.model.Atendimento;
import com.vetweb.model.ElementoProntuario;
import com.vetweb.model.Patologia;
import com.vetweb.model.Prontuario;
import com.vetweb.model.ProntuarioPatologia;
import com.vetweb.model.ProntuarioVacina;
import com.vetweb.model.TipoDeAtendimento;
import com.vetweb.model.Vacina;
import com.vetweb.model.pojo.ElementoHistorico;
import com.vetweb.model.pojo.TipoElementoHistorico;
import com.vetweb.service.EmailService;

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
    private EmailService emailService;
    
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
    
    @RequestMapping(value = "/prontuarioDoAnimal/{animalId}", method = RequestMethod.GET)
    public ModelAndView prontuarioDoAnimal(@PathVariable("animalId") final Long animalId, @ModelAttribute("atendimento") Atendimento atendimento,
    		@ModelAttribute("prontuarioPatologia") Patologia patologia, @ModelAttribute("prontuarioVacina") Vacina vacina) {
        ModelAndView modelAndView = new ModelAndView("prontuario/prontuario");
        Prontuario prontuario = prontuarioDAO.buscarProntuarioPorAnimal(animalId);
		modelAndView.addObject("prontuario", prontuario);
		modelAndView.addObject("tiposDeAtendimento", atendimentoDAO.buscarTiposDeAtendimento());
		modelAndView.addObject("vacinas", prontuarioDAO.buscarVacinas().stream()
	    		.map(vac -> vac.getNome()).collect(Collectors.toList()));
		modelAndView.addObject("patologias", animalDAO.buscarPatologias().stream()
	    		.map(pat -> pat.getNome()).collect(Collectors.toList()));
		List<ElementoHistorico> elementosHistorico = new ArrayList<>();
		prontuario.getAtendimentos()
			.forEach(at -> elementosHistorico
					.add(new ElementoHistorico(at.getAtendimentoId(), at.getTipoDeAtendimento().getNome(), at.getDataAtendimento(), TipoElementoHistorico.ATENDIMENTO)));
		prontuario.getPatologias()
			.forEach(pat -> elementosHistorico
					.add(new ElementoHistorico(pat.getProntuarioPatologiaId(), pat.getPatologia().getNome(), pat.getInclusaoPatologia(), TipoElementoHistorico.PATOLOGIA)));
		prontuario.getVacinas()
			.forEach(vac -> elementosHistorico
					.add(new ElementoHistorico(vac.getProntuarioVacinaId(), vac.getVacina().getNome(), vac.getInclusaoVacina(), TipoElementoHistorico.VACINA)));
		modelAndView.addObject("historico", elementosHistorico);
        return modelAndView;
    }
    
    @RequestMapping(value = "/adicionarAtendimento", method = RequestMethod.POST)
    public ModelAndView adcAtendimento(@ModelAttribute("atendimento") Atendimento atendimento,
    		@RequestParam("prontuarioId") final Long prontuarioId) {
    	Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
        prontuarioDAO.salvarAtendimento(atendimento);
        prontuarioDAO.adicionarAtendimento(atendimento, prontuarioId);
        notificaCliente(atendimento, prontuario);
        return new ModelAndView("redirect:prontuarioDoAnimal/" + prontuario.getAnimal().getAnimalId());
    }

	@SuppressWarnings("static-access")
	private void notificaCliente(ElementoProntuario elementoProntuario, Prontuario prontuario) {
		emailService.enviar(prontuario.getAnimal().getProprietario(),
        		"Foi feita uma nova inclusao de " + elementoProntuario
        		+ " ao prontuario do seu animal " + prontuario.getAnimal().getNome() + "",
        		"Inclusão de " + elementoProntuario + "");
	}
    
    @RequestMapping(value="/adicionarPatologia", method=RequestMethod.POST)
    public ModelAndView adcPatologia(@RequestParam("prontuarioId") final Long prontuarioId,
    		@RequestParam("patologia") final String patologiaStr, 
    		@RequestParam("prontuarioPatologiaId") final Long prontuarioPatologiaId,
    	@RequestParam("inclusaoPatologia") final String inclusaoPatologia) {
    	LOGGER.info(("Inserindo patologia " + patologiaStr + " no prontuário " + prontuarioId).toUpperCase());
    	ProntuarioPatologia prontuarioPatologia = new ProntuarioPatologia();
    	Patologia pat = animalDAO.buscarPatologiaPorDescricao(patologiaStr);
		animalDAO.salvarPatologia(pat);
		if (prontuarioPatologiaId != null) {
			prontuarioPatologia = prontuarioDAO.buscarOcorrenciaDaPatologia(prontuarioPatologiaId);
		}
		prontuarioPatologia.setPatologia(pat);
		Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
		prontuarioPatologia.setProntuario(prontuario);
		prontuarioPatologia.setInclusaoPatologia(LocalDate.parse(inclusaoPatologia, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    	prontuarioDAO.adicionarPatologia(prontuarioPatologia, prontuarioId);
    	notificaCliente(prontuarioPatologia, prontuario);
    	return new ModelAndView("redirect:prontuarioDoAnimal/" + prontuario.getAnimal().getAnimalId());
    }
    
    @RequestMapping(value="/adicionarVacina", method=RequestMethod.POST)
    public ModelAndView adcVacina(@RequestParam("prontuarioId") final Long prontuarioId,
    		@RequestParam("vacina") final String vacinaStr,
    		@RequestParam("prontuarioVacinaId") final Long prontuarioVacinaId,
    		@RequestParam("inclusaoVacina") final String inclusaoVacina) {
    	LOGGER.info(("Inserindo vacina " + vacinaStr + " no prontuário " + prontuarioId).toUpperCase());
    	ProntuarioVacina prontuarioVacina = new ProntuarioVacina();
    	Vacina vacina = vacinaDAO.buscarPorNome(vacinaStr);
		vacinaDAO.salvar(vacina);
		if (prontuarioVacinaId != null)
			prontuarioVacina = prontuarioDAO.buscarOcorrenciaDaVacina(prontuarioVacinaId);
		prontuarioVacina.setVacina(vacina);
		Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
		prontuarioVacina.setProntuario(prontuario);
		prontuarioVacina.setInclusaoVacina(LocalDate.parse(inclusaoVacina, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    	prontuarioDAO.adicionarVacina(prontuarioVacina, prontuarioId);
    	notificaCliente(prontuarioVacina, prontuario);
    	return new ModelAndView("redirect:prontuarioDoAnimal/" + prontuarioDAO.buscarPorId(prontuarioId).getAnimal().getAnimalId());
    }
    
    @RequestMapping(value = "/removerAtendimentoDoProntuario/{prontuarioId}/{atendimentoId}", method = RequestMethod.GET)
    public ModelAndView removerAtendimentoDoProntuario(@PathVariable("prontuarioId")Long prontuarioId, @PathVariable("atendimentoId")Long atendimentoId,
    		@ModelAttribute("atendimento") Atendimento atendimento,
    		@ModelAttribute("prontuarioPatologia") Patologia patologia,
    		@ModelAttribute("prontuarioVacina") Vacina vacina) {
    	Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
    	ModelAndView modelAndView = new ModelAndView("redirect:/prontuario/prontuarioDoAnimal/" + prontuario.getAnimal().getAnimalId());
		modelAndView.addObject("prontuario", prontuario);
    	prontuarioDAO.removerAtendimento(atendimentoDAO.buscarPorId(atendimentoId), prontuarioId);
    	adicionarListasAoProntuario(modelAndView);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/removerVacinaDoProntuario/{prontuarioId}/{vacinaId}", method = RequestMethod.GET)
    public ModelAndView removerVacinaDoProntuario(@PathVariable("prontuarioId")Long prontuarioId, @PathVariable("vacinaId")Long vacinaId,
    		@RequestParam("inclusaoOcorrenciaVacina") String inclusaoVacina,
    		@ModelAttribute("atendimento") Atendimento atendimento,
    		@ModelAttribute("prontuarioPatologia") Patologia patologia,
    		@ModelAttribute("prontuarioVacina") Vacina vacina) {
    	Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
    	ModelAndView modelAndView = new ModelAndView("redirect:/prontuario/prontuarioDoAnimal/" + prontuario.getAnimal().getAnimalId());
		modelAndView.addObject("prontuario", prontuario);
    	prontuarioDAO.removerVacina(prontuarioDAO.buscarOcorrenciaDaVacina(vacinaId),prontuarioId);
    	adicionarListasAoProntuario(modelAndView);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/removerPatologiaDoProntuario/{prontuarioId}/{patologiaId}", method = RequestMethod.GET)
    public ModelAndView removerPatologiaDoProntuario(@PathVariable("prontuarioId")Long prontuarioId, @PathVariable("patologiaId")Long patologiaId,
    		@ModelAttribute("atendimento") Atendimento atendimento,
    		@ModelAttribute("prontuarioPatologia") Patologia patologia,
    		@ModelAttribute("prontuarioVacina") Vacina vacina) {
    	Prontuario prontuario = prontuarioDAO.buscarPorId(prontuarioId);
    	ModelAndView modelAndView = new ModelAndView("redirect:/prontuario/prontuarioDoAnimal/" + prontuario.getAnimal().getAnimalId());
		modelAndView.addObject("prontuario", prontuario);
    	prontuarioDAO.removerPatologia(prontuarioDAO.buscarOcorrenciaDaPatologia(patologiaId),prontuarioId);
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
    	viewProntuario.addAllObjects(listasProntuario);
    }
    
}
