package com.vetweb.controller;
//@author renan.rodrigues@metasix.com.br

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.vetweb.dao.AnimalDAO;
import com.vetweb.dao.ProntuarioDAO;
import com.vetweb.dao.ProprietarioDAO;
import com.vetweb.model.Animal;
import com.vetweb.model.Proprietario;
import com.vetweb.model.pojo.Pais;
import com.vetweb.model.pojo.Profissao;
import com.vetweb.model.validators.ProprietarioValidator;

@Controller
@Transactional
@RequestMapping("/clientes")
public class ProprietarioController {
	
    @Autowired
    private ProprietarioDAO proprietarioDAO;
    
    @Autowired
    private AnimalDAO animalDAO;
    
    @Autowired
    private ProntuarioDAO prontuarioDAO;
    
    private static final Logger LOGGER = Logger.getLogger(ProprietarioController.class);
    
    private static List<Pais> paises;
    
    private static Profissao profissao;
    
    @PostConstruct
    public void readFiles() {
    	try{
    		ClassPathResource paisesResource = new ClassPathResource("paises.json");
    		ClassPathResource profissoes = new ClassPathResource("profissoes.json");
    		ObjectMapper objectMapper = new ObjectMapper();
			profissao = objectMapper.readValue(IOUtils.toString(profissoes.getInputStream()), Profissao.class);
			TypeFactory typeFactory = TypeFactory.defaultInstance();
			paises = objectMapper.readValue(paisesResource.getInputStream(), typeFactory.constructCollectionType(List.class, Pais.class));
    		LOGGER.info(paises);
    		LOGGER.info(profissao.getProfissoes());
    	} catch(IOException exception){
    		LOGGER.error(exception);
    	}
    }
    
    @RequestMapping("/cadastro")
    public ModelAndView form(Proprietario proprietario){
        ModelAndView modelAndView = new ModelAndView("proprietario/cadastroProprietario");
        modelAndView.addObject("paises", paises);
        modelAndView.addObject("profissoes", profissao.getProfissoes());
        return modelAndView;
    }
    
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public ModelAndView cadastrar(@Valid @ModelAttribute("proprietario") Proprietario proprietario, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:listar");
        if(bindingResult.hasErrors()){
            return form(proprietario);
        }
        try{
            LOGGER.info("Proprietario" + proprietario);
            proprietarioDAO.salvar(proprietario);
        } catch (Exception exception){
        	LOGGER.error(exception);
        }
        return modelAndView;
    }
    
    @RequestMapping("/listar")
    public ModelAndView proprietarios(){
        ModelAndView modelAndView = new ModelAndView("proprietario/proprietarios");
        modelAndView.addObject("proprietarios", proprietarioDAO.listarTodos());
        return modelAndView;
    }
    
	@RequestMapping(value = "/remover/{pessoaId}")
    public ModelAndView remover(@PathVariable("pessoaId") long pessoaId){
        ModelAndView modelAndView = new ModelAndView("redirect:/clientes/listar");
        Proprietario p = proprietarioDAO.buscarPorId(pessoaId);
        try{
            p.getAnimais().stream().forEach(a -> {
            	
                prontuarioDAO.remover(prontuarioDAO.buscarProntuarioPorAnimal(a.getAnimalId()));
            });
            LOGGER.info(("Prontuários dos animais do " + p.getNome() + " eliminados.").toUpperCase());
            p.getAnimais().stream().forEach(a -> animalDAO.remover(a));
            LOGGER.info(("Animais do cliente " + p.getNome() + " removidos com sucesso.").toUpperCase());
            proprietarioDAO.remover(p);
            LOGGER.info(("Proprietário " + p.getNome() + " removido com sucesso. ").toUpperCase());
        }catch(Exception e){
            LOGGER.error(e);
        }
        return modelAndView;
    }
	
    @RequestMapping(value = "/atualizar/{pessoaId}")
    public ModelAndView atualizar(@PathVariable("pessoaId") long pessoaId){
        ModelAndView modelAndView = new ModelAndView("proprietario/cadastroProprietario");
        modelAndView.addObject("proprietario", proprietarioDAO.buscarPorId(pessoaId));
        return modelAndView;
    } 
    
    @RequestMapping(value = "/addAnimal/{pessoaId}", method = RequestMethod.GET)
    public ModelAndView addAnimal(@PathVariable("pessoaId") long pessoaId, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/animais/cadastro");
        attributes.addAttribute("desabilitaTrocaProprietario", true);
        Proprietario p = proprietarioDAO.buscarPorId(pessoaId);
        attributes.addAttribute("proprietario", p.getNome());
        return modelAndView;
    }
    
    @RequestMapping(value = "/financeiro/{pessoaId}", method = RequestMethod.GET)
    public ModelAndView financeiroCliente(@PathVariable("pessoaId") Long clienteId) {
    	ModelAndView modelAndView = new ModelAndView("proprietario/balancoCliente");
    	Proprietario proprietario = proprietarioDAO.buscarPorId(clienteId);
    	BigDecimal totalPendente = proprietarioDAO.buscarValorPendenteDoCliente(proprietario);
    	Long codigoCliente = proprietario.getPessoaId();
		modelAndView.addObject("atendimentosFeitos", proprietarioDAO.buscarAtendimentosParaOCliente(codigoCliente));
    	modelAndView.addObject("vacinasAplicadas", proprietarioDAO.buscarVacinasParaOCliente(codigoCliente));
    	modelAndView.addObject("examesRealizados", proprietarioDAO.buscarExamesParaOCliente(codigoCliente));
    	modelAndView.addObject("totalPendente", totalPendente);
    	boolean contemDebito = totalPendente.intValue() > 0;
    	modelAndView.addObject("proprietario", proprietario);
    	modelAndView.addObject("contemDebito", contemDebito);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/detalhesCliente/{pessoaId}", method = RequestMethod.GET)
    public ModelAndView detalhesCliente(@PathVariable("pessoaId") long pessoaId) {
        Proprietario proprietario = proprietarioDAO.buscarPorId(pessoaId);
        ModelAndView modelAndView = new ModelAndView("proprietario/detalhesCliente");
        modelAndView.addObject(proprietario);
        modelAndView.addObject("idadeCliente", ChronoUnit.YEARS.between(proprietario.getNascimento(), LocalDate.now()));
        return modelAndView;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder, HttpServletRequest request, ServletRequestDataBinder requestDataBinder) {
        binder.setValidator(new ProprietarioValidator());
        requestDataBinder.registerCustomEditor(Proprietario.class, "proprietario", new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Proprietario proprietario = proprietarioDAO.buscarPorNome(text);
                setValue(proprietario);
            }
            @Override
            public String getAsText() {
                Proprietario proprietario = (Proprietario)this.getValue();
                if(proprietario != null)
                    return proprietario.getNome();
                else
                    return "Proprietario";
            }
        });
        requestDataBinder.registerCustomEditor(Animal.class, "animal", new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Animal a = animalDAO.buscarPorNome(text);
                this.setValue(a);
            }
            @Override
            public String getAsText() {
                Animal a = (Animal)this.getValue();
                if(a != null)
                    return a.getNome();
                else
                    return "Animal";
            }
        });        
    }
    
}
