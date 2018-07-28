package com.vetweb.controller;
// @author renan.rodrigues@metasix.com.br


import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.vetweb.dao.AnimalDAO;
import com.vetweb.dao.ProntuarioDAO;
import com.vetweb.dao.ProprietarioDAO;
import com.vetweb.model.Animal;
import com.vetweb.model.Especie;
import com.vetweb.model.Patologia;
import com.vetweb.model.Pelagem;
import com.vetweb.model.Raca;
import com.vetweb.service.FileService;

@Controller
@Transactional
@RequestMapping("/animais")
public class AnimalController {
    
    @Autowired
    private AnimalDAO animalDAO;
    
    @Autowired
    private ProprietarioDAO proprietarioDAO;
    
    @Autowired
    private ProntuarioDAO prontuarioDAO;
    
    @Autowired @Qualifier("amazon")
    private FileService arquivoService;
    
    private static final Logger LOGGER = Logger.getLogger(AnimalController.class);
    
    public static String modelDML = null;
    
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ModelAndView listarTodos() {
        ModelAndView modelAndView = new ModelAndView("animal/animais");
        modelAndView.addObject("animais", animalDAO.listarTodos());
        return modelAndView;
    }
    
    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public synchronized ModelAndView form(Animal animal, @RequestParam("desabilitaTrocaProprietario") final boolean desabilitaTrocaProprietario) {
        ModelAndView modelAndView = new ModelAndView("animal/cadastroAnimal");
        modelAndView.addObject("proprietarios", proprietarioDAO.listarTodos());
        modelAndView.addObject("especies", animalDAO.buscarEspecies());
        modelAndView.addObject("pelagens", animalDAO.buscarPelagens());
        modelAndView.addObject("desabilitaTrocaProprietario", desabilitaTrocaProprietario);
        return modelAndView;
    }
    
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public synchronized ModelAndView salvar(@Valid @ModelAttribute("animal") Animal animal,
    		BindingResult bindingResult,
    		MultipartFile imagemFile) {
        if (bindingResult.hasErrors()) {
            LOGGER.error(bindingResult.getAllErrors());
            return form(animal, true);
        }
        try {
            String caminhoImagem = arquivoService.salvarArquivo(imagemFile); 
            animal.setImagem(caminhoImagem);
            animalDAO.salvar(animal);
            LOGGER.info(("Animal " + animal.getNome() + " inserido com sucesso na base.").toUpperCase());
        } catch (Exception exception) {
            LOGGER.error(("Não foi possível inserir o animal " + animal.getNome() + " na base. ").toUpperCase(),
                    exception);
        }
        LOGGER.info(("Animal " + animal + " sendo encaminhado para criação do prontuário.").toUpperCase());
        ModelAndView modelAndView = new ModelAndView("forward:/prontuario/gerarProntuario?animalId=" + animal.getAnimalId());
        return modelAndView;
    }
    
    @RequestMapping(value = "/remover/{animalId}")
    public ModelAndView remover(@PathVariable("animalId") long animalId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/animais/listar");
        modelDML = "Animal";
        try {
            LOGGER.info(("Eliminando prontuário do animal " + animalDAO.buscarPorId(animalId).getNome()).toUpperCase());
            prontuarioDAO.remover(prontuarioDAO.buscarProntuarioPorAnimal(animalId));
            proprietarioDAO.removerAnimal(animalId);
        } catch (Exception e) {
            LOGGER.error(("Erro na remoção do animal " + animalId + ". ").toUpperCase(), e);
        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/atualizar/{animalId}", method = RequestMethod.GET)
    public ModelAndView atualizar(@PathVariable("animalId") long animalId) {
        ModelAndView modelAndView = new ModelAndView("animal/cadastroAnimal");
        modelAndView.addObject("animal", animalDAO.buscarPorId(animalId));
        modelAndView.addObject("proprietarios", proprietarioDAO.listarTodos());
        modelAndView.addObject("desabilitaTrocaProprietario", true);
        return modelAndView;
    }
    
    @RequestMapping(value = "/atualizarEspecie/{especieId}", method = RequestMethod.GET)
    public ModelAndView atualizarEspecie(@PathVariable("especieId") long especieId) {
        ModelAndView modelAndView = new ModelAndView("animal/cadastroEspecie");
        Especie especie = animalDAO.buscarEspeciePorId(especieId);
        modelAndView.addObject("especie", especie);
        return modelAndView;
    }
    
    @RequestMapping(value = "/atualizarRaca/{racaId}", method = RequestMethod.GET)
    public ModelAndView atualizarRaca(@PathVariable("racaId") long racaId) {
        ModelAndView modelAndView = new ModelAndView("animal/cadastroRaca");
        Raca raca = animalDAO.buscarRacaPorId(racaId);
        modelAndView.addObject("raca", raca);
        modelAndView.addObject("especies", animalDAO.buscarEspecies());
        return modelAndView;
    }
    
    @RequestMapping(value = "/atualizarPelagem/{pelagemId}", method = RequestMethod.GET)
    public ModelAndView atualizarPelagem(@PathVariable("pelagemId") long pelagemId) {
        ModelAndView modelAndView = new ModelAndView("animal/cadastroPelagem");
        Pelagem p = animalDAO.buscarPelagemPorId(pelagemId);
        modelAndView.addObject("pelagem", p);
        return modelAndView;
    }
    
    @RequestMapping(value = "/atualizarPatologia/{patologiaId}", method = RequestMethod.GET)
    public ModelAndView atualizarPatologia(@PathVariable("patologiaId") Long patologiaId) {
        ModelAndView modelAndView = new ModelAndView("animal/cadastroPatologia");
        Patologia patologia = animalDAO.buscarPatologiaPorId(patologiaId);
        modelAndView.addObject("patologia", patologia);
        return modelAndView;
    }
    
    @RequestMapping(value = "/detalhesAnimal/{nomeAnimal}", method = RequestMethod.GET)
    public ModelAndView paginaDetalhes(@PathVariable("nomeAnimal") String nomeAnimal) {
        ModelAndView modelAndView = new ModelAndView("animal/detalhesAnimal");
        try{
        	LOGGER.info(("Animal " + nomeAnimal + " encontrado na base de dados. ").toUpperCase());
            Animal animal = animalDAO.buscarPorNome(nomeAnimal.trim());
			modelAndView.addObject("animal", animal);
        } catch (RuntimeException exception){LOGGER.error(("Animal " + nomeAnimal + " não encontrado na base de dados. ").toUpperCase());}
        return modelAndView;
    }
    
    @RequestMapping(value = "/especies", method = RequestMethod.GET)
    public ModelAndView buscarEspecies(){
        ModelAndView modelAndView = new ModelAndView("animal/especies");
        modelAndView.addObject("especies", animalDAO.buscarEspecies());
        return modelAndView;
    }
    
    @RequestMapping(value = "/cadastroEspecie", method = RequestMethod.GET)
    public ModelAndView cadastroEspecie(@ModelAttribute("especie") Especie especie){
        ModelAndView modelAndView = new ModelAndView("animal/cadastroEspecie");
        return modelAndView;
    }
    
    @RequestMapping(value = "/cadastroPelagem", method = RequestMethod.GET)
    public ModelAndView cadastroPelagem(@ModelAttribute("pelagem") Pelagem pelagem){
        ModelAndView modelAndView = new ModelAndView("animal/cadastroPelagem");
        return modelAndView;
    }
    
    @RequestMapping(value = "/addEspecie", method = RequestMethod.POST)
    public ModelAndView addEspecie(@ModelAttribute("especie")Especie especie){
        ModelAndView modelAndView = new ModelAndView("redirect:especies");        
        animalDAO.salvarEspecie(especie);
        return modelAndView;
    }
    
    @RequestMapping(value = "/addPatologia", method = RequestMethod.POST)
    public ModelAndView addPatologia(@ModelAttribute("patologia")Patologia patologia){
        ModelAndView modelAndView = new ModelAndView("redirect:patologias");        
        animalDAO.salvarPatologia(patologia);
        return modelAndView;
    }
    
    @RequestMapping(value = "/cadastroRaca", method = RequestMethod.GET)
    public ModelAndView cadastroRaca(@ModelAttribute("raca")Raca raca){
        ModelAndView modelAndView = new ModelAndView("animal/cadastroRaca");
        modelAndView.addObject("especies", animalDAO.buscarEspecies());
        return modelAndView;
    }
    
    @RequestMapping(value = "/cadastroPatologia", method = RequestMethod.GET)
    public ModelAndView cadastroPatologia(@ModelAttribute("patologia")Patologia patologia){
        ModelAndView modelAndView = new ModelAndView("animal/cadastroPatologia");
        modelAndView.addObject("patologias", animalDAO.buscarPatologias());
        return modelAndView;
    }
    
    @RequestMapping(value = "/addPelagem", method = RequestMethod.POST)
    public ModelAndView addPelagem(@ModelAttribute("pelagem")Pelagem pelagem){
        ModelAndView modelAndView = new ModelAndView("redirect:pelagens");
        animalDAO.salvarPelagem(pelagem);
        return modelAndView;
    }
    
    @RequestMapping(value = "/addRaca", method = RequestMethod.POST)
    public ModelAndView addRaca(@ModelAttribute("raca")Raca raca){
        ModelAndView modelAndView = new ModelAndView("redirect:racas");
        animalDAO.salvarRaca(raca);
        return modelAndView;
    }
    
    @RequestMapping(value = "/pelagens", method = RequestMethod.GET)
    public ModelAndView buscarPelagens(){
        ModelAndView modelAndView = new ModelAndView("animal/pelagens");
        modelAndView.addObject("pelagens", animalDAO.buscarPelagens());
        return modelAndView;
    }
    
    @RequestMapping(value = "/racas", method = RequestMethod.GET)
    public ModelAndView buscarRacas(){
        ModelAndView modelAndView = new ModelAndView("animal/racas");
        modelAndView.addObject("racas", animalDAO.buscarRacas());
        return modelAndView;
    }
    
    @RequestMapping(value = "/patologias", method = RequestMethod.GET)
    public ModelAndView buscarPatologias(){
        ModelAndView modelAndView = new ModelAndView("animal/patologias");
        modelAndView.addObject("patologias", animalDAO.buscarPatologias());
        return modelAndView;
    }
    
    @RequestMapping(value = "/removerEspecie/{especieId}", method = RequestMethod.GET)
    public ModelAndView delEspecie(@PathVariable("especieId") Long especieId){
        modelDML = "Especie";
        ModelAndView modelAndView = new ModelAndView("redirect:/animais/especies");
        animalDAO.removerEspecie(animalDAO.buscarEspeciePorId(especieId));
        return modelAndView;
    }
    
    @RequestMapping(value = "/removerRaca/{racaId}", method = RequestMethod.GET)
    public ModelAndView delRaca(@PathVariable("racaId") Long racaId){
        modelDML = "Raca";
        ModelAndView modelAndView = new ModelAndView("redirect:/animais/racas");
        animalDAO.removerRaca(animalDAO.buscarRacaPorId(racaId));
        return modelAndView;
    }
    
    @RequestMapping(value = "/removerPelagem/{pelagemId}", method = RequestMethod.GET)
    public ModelAndView delPelagem(@PathVariable("pelagemId") Long pelagemId){
        modelDML = "Pelagem";
        Pelagem p = animalDAO.buscarPelagemPorId(pelagemId);
        ModelAndView modelAndView = new ModelAndView("redirect:/animais/pelagens");
        animalDAO.removerPelagem(p);
        return modelAndView;
    }
    
    @RequestMapping(value = "/removerPatologia/{patologiaId}", method = RequestMethod.GET)
    public ModelAndView delPatologia(@PathVariable("patologiaId") Long patologiaId){
        modelDML = "Patologia";
        Patologia p = animalDAO.buscarPatologiaPorId(patologiaId);
        ModelAndView modelAndView = new ModelAndView("redirect:/animais/patologias");
        animalDAO.removerPatologia(p);
        return modelAndView;
    }
    
}
