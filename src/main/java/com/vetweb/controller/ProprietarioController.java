package com.vetweb.controller;
 import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

// @author 11151504898
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetweb.dao.AnimalDAO;
import com.vetweb.dao.ProntuarioDAO;
import com.vetweb.dao.ProprietarioDAO;
import com.vetweb.model.Animal;
import com.vetweb.model.Atendimento;
import com.vetweb.model.Prontuario;
import com.vetweb.model.ProntuarioVacina;
import com.vetweb.model.Proprietario;
import com.vetweb.model.pojo.Pais;
import com.vetweb.model.pojo.Profissao;
import com.vetweb.model.validators.ProprietarioValidator;

@Controller
@Transactional//Indica que os métodos da Controller necessitam de transação
@RequestMapping("/clientes")//Caminho base da Controller
public class ProprietarioController {
	
    @Autowired//Faz com que Spring gerencie o recurso automaticamente (Crie o objeto/Injeção de dependência)
    ProprietarioDAO proprietarioDAO;
    
    @Autowired
    AnimalDAO animalDAO;
    
    @Autowired
    ProntuarioDAO prontuarioDAO;
    
    private static final Logger LOGGER = Logger.getLogger(ProprietarioController.class);
    
    static List<Pais> paises;
    
    static Profissao profissao;
    {
//        File json = new File("C:\\Users\\renan\\Desktop\\VetWeb\\src\\main\\webapp\\resources\\paises.json");
        try{
//            Path paisesJson = Paths.get(System.getenv("catalina.base"), "vetwebFiles", "paises.json");
//            Path profissoesJson = Paths.get(System.getenv("catalina.base"), "vetwebFiles", "profissoes.json");
            Path paisesJson = Paths.get(System.getenv("catalina_base"), "vetwebFiles", "paises.json");
            Path profissoesJson = Paths.get(System.getenv("catalina_base"), "vetwebFiles", "profissoes.json");
            ObjectMapper objectMapper = new ObjectMapper();
            paises = objectMapper.readValue(Files.newInputStream(paisesJson, StandardOpenOption.READ), new TypeReference<List<Pais>>() {});
            profissao = objectMapper.readValue(Files.newInputStream(profissoesJson, StandardOpenOption.READ), Profissao.class);
            LOGGER.info(paises);
            LOGGER.info(profissao.getProfissoes());
        } catch(IOException exception){
            LOGGER.error(exception);
        }
    }
    
    @RequestMapping("/cadastro")//URL da página.    Composta por caminho base Controller + URL
    public ModelAndView formCadastro(Proprietario proprietario){//Recebe objeto modelo como parâmetro
        ModelAndView modelAndView = new ModelAndView("proprietario/cadastroProprietario");//A diretorio base e WEB-INF. Por isso deve informar pasta/página
        modelAndView.addObject("paises", paises);
        modelAndView.addObject("profissoes", profissao.getProfissoes());
        return modelAndView;
    }
    
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public ModelAndView cadastrar(@Valid @ModelAttribute("proprietario") Proprietario proprietario, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:listar");
        if(bindingResult.hasErrors()){
            return formCadastro(proprietario);
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
        modelAndView.addObject("proprietarios", proprietarioDAO.listar());
        return modelAndView;
    }
    
	@RequestMapping(value = "/remover/{pessoaId}")
    public ModelAndView remover(@PathVariable("pessoaId") long pessoaId){//${animal.animalId}
        ModelAndView modelAndView = new ModelAndView("redirect:/clientes/listar");
        Proprietario p = proprietarioDAO.consultarPorId(pessoaId);
        try{
            p.getAnimais().stream().forEach(a -> {
            	
                prontuarioDAO.remover(prontuarioDAO.prontuarioPorAnimal(a.getAnimalId()));
            });
            LOGGER.info(("Prontuários dos animais do " + p.getNome() + " eliminados.").toUpperCase());
            p.getAnimais().stream().forEach(a -> animalDAO.remover(a));//Remove os animais do clientes antes de remove-lo
            LOGGER.info(("Animais do cliente " + p.getNome() + " removidos com sucesso.").toUpperCase());
            proprietarioDAO.remover(p);
            LOGGER.info(("Proprietário " + p.getNome() + " removido com sucesso. ").toUpperCase());
        }catch(Exception e){
            LOGGER.error(e);
        }
        return modelAndView;
    }
	
    @RequestMapping(value = "/atualizar/{pessoaId}")//Definindo um URI template. O valor de pessoaId é acessado c/ PathVariable
    public ModelAndView atualizar(@PathVariable("pessoaId") long pessoaId){//PathVariable:  Permite acessar o valor passado na URI template
        ModelAndView modelAndView = new ModelAndView("proprietario/cadastroProprietario");
        modelAndView.addObject("proprietario", proprietarioDAO.consultarPorId(pessoaId));
        return modelAndView;
    } 
    
    @RequestMapping(value = "/addAnimal/{pessoaId}", method = RequestMethod.GET)
    public ModelAndView addAnimal(@PathVariable("pessoaId") long pessoaId, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/animais/cadastro");
        attributes.addAttribute("desabilitaTrocaProprietario", true);
        Proprietario p = proprietarioDAO.consultarPorId(pessoaId);
        attributes.addAttribute("proprietario", p.getNome());
        return modelAndView;
    }
    
    @RequestMapping(value = "/financeiro/{pessoaId}", method = RequestMethod.GET)
    public ModelAndView financeiroCliente(@PathVariable("pessoaId") Long clienteId) {
    	ModelAndView modelAndView = new ModelAndView("proprietario/balancoCliente");
    	List<Prontuario> prontuariosCliente = proprietarioDAO.getBalancoFinanceiro(clienteId);
    	List<Atendimento> atendimentosAnimaisCliente = prontuariosCliente.stream()
    			.flatMap(p -> p.getAtendimentos().stream()).collect(Collectors.toList());
    	List<ProntuarioVacina> vacinasAnimaisCliente = prontuariosCliente.stream()
    			.flatMap(p -> p.getVacinas().stream()).collect(Collectors.toList());
    	modelAndView.addObject("atendimentosFeitos", atendimentosAnimaisCliente);
    	modelAndView.addObject("vacinasAplicadas", vacinasAnimaisCliente);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/detalhesCliente/{pessoaId}", method = RequestMethod.GET)
    public ModelAndView detalhesCliente(@PathVariable("pessoaId") long pessoaId) {
        Proprietario proprietario = proprietarioDAO.consultarPorId(pessoaId);
        ModelAndView modelAndView = new ModelAndView("proprietario/detalhesCliente");
        modelAndView.addObject(proprietario);
        modelAndView.addObject("idadeCliente", ChronoUnit.YEARS.between(proprietario.getNascimento(), LocalDate.now()));
        return modelAndView;
    }
    
    @InitBinder//Método invocado a cada request neste Controller
    public void initBinder(WebDataBinder binder, HttpServletRequest request, ServletRequestDataBinder requestDataBinder) {//Para configuração do formato de data reconhecido na Controller
        binder.setValidator(new ProprietarioValidator());
        requestDataBinder.registerCustomEditor(Proprietario.class, "proprietario", new PropertyEditorSupport(){
            @Override//Estratégia de conversão do formulário (Vem como texto) para objeto
            public void setAsText(String text) throws IllegalArgumentException {
                Proprietario proprietario = proprietarioDAO.consultarPorNome(text);
                setValue(proprietario);
            }
            @Override//Estratégia de conversão do objeto para texto
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
                Animal a = animalDAO.consultarPorNome(text);
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
