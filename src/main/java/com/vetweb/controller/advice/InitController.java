package com.vetweb.controller.advice;
//@author renan.rodrigues@metasix.com.br

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.vetweb.dao.AnimalDAO;
import com.vetweb.dao.AtendimentoDAO;
import com.vetweb.dao.ConfigDAO;
import com.vetweb.dao.ProntuarioDAO;
import com.vetweb.dao.ProprietarioDAO;
import com.vetweb.dao.VacinaDAO;
import com.vetweb.model.Animal;
import com.vetweb.model.Atendimento;
import com.vetweb.model.Clinica;
import com.vetweb.model.Especie;
import com.vetweb.model.Patologia;
import com.vetweb.model.Pelagem;
import com.vetweb.model.Prontuario;
import com.vetweb.model.Proprietario;
import com.vetweb.model.Raca;
import com.vetweb.model.TipoDeAtendimento;
import com.vetweb.model.Vacina;

@ControllerAdvice
@Transactional
public class InitController {
	
    @Autowired
    private AnimalDAO animalDAO;
    
    @Autowired
    private ProprietarioDAO proprietarioDAO;
    
    @Autowired
    private ProntuarioDAO prontuarioDAO;
    
    @Autowired
    private AtendimentoDAO atendimentoDAO;
    
    @Autowired
    private VacinaDAO vacinaDAO;
    
    @Autowired 
    private ConfigDAO configDAO;
    
    @Autowired
    private ServletContext servletContext;
    
    private static final Logger LOGGER = Logger.getLogger(InitController.class);
    
    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
    	LOGGER.info("EXECUTANDO MÉTODO @InitBinder");
        configDAO.salvarClinica(new Clinica(servletContext.getInitParameter("razaoSocial"),
                LocalDate.parse(servletContext.getInitParameter("fundadaEm"), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                servletContext.getInitParameter("cnpj"), servletContext.getInitParameter("proprietario")));
        
        binder.registerCustomEditor(Proprietario.class, "proprietario", new PropertyEditorSupport(){
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
        
        binder.registerCustomEditor(Animal.class, "animal", new PropertyEditorSupport(){
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
        
        binder.registerCustomEditor(Especie.class, "especie", new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Especie especie = animalDAO.buscarEspeciePorDescricao(text);
                this.setValue(especie);
            }

            @Override
            public String getAsText() {
                Especie especie = (Especie)this.getValue();
                if (especie != null) return especie.getDescricao();
                else return "especie";
            }
        });
        
        binder.registerCustomEditor(Raca.class, "raca", new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Raca raca = animalDAO.buscarRacaPorDescricao(text);
                this.setValue(raca);
            }

            @Override
            public String getAsText() {
                Raca raca = (Raca)this.getValue();
                if (raca != null) return raca.getDescricao();
                else return "raca";
            }
        });
        
        binder.registerCustomEditor(Pelagem.class, "pelagem", new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Pelagem p = animalDAO.buscarPelagemPorDescricao(text);
                this.setValue(p);
            }

            @Override
            public String getAsText() {
                Pelagem pelagem = (Pelagem)this.getValue();
                if (pelagem != null) return pelagem.getDescricao();
                else return "pelagem";
            }
        });
        
        binder.registerCustomEditor(Prontuario.class, "prontuario", new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Prontuario prontuario = prontuarioDAO.buscarProntuarioPorAnimal(animalDAO.buscarPorNome(text).getAnimalId());
                this.setValue(prontuario);
            }

            @Override
            public String getAsText() {
                Prontuario prontuario = (Prontuario)this.getValue();
                if (prontuario != null) return "prontuario " + prontuario.getAnimal().getNome();
                else return "prontuario";
            }
        });
        
        binder.registerCustomEditor(TipoDeAtendimento.class, "tipoDeAtendimento", new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                TipoDeAtendimento tipoDeAtendimento = atendimentoDAO.buscarTipoDeAtendimentoPorNome(text);
                this.setValue(tipoDeAtendimento);
            }

            @Override
            public String getAsText() {
                TipoDeAtendimento tipoDeAtendimento = (TipoDeAtendimento)this.getValue();
                if (tipoDeAtendimento != null) return tipoDeAtendimento.getNome();
                else return "tipoDeAtendimento";
            }
        });
        
        binder.registerCustomEditor(Vacina.class, "vacina", new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Vacina vacina = vacinaDAO.buscarPorNome(text);
                this.setValue(vacina);
            }

            @Override
            public String getAsText() {
                Vacina vacina = (Vacina)this.getValue();
                if (vacina != null) return vacina.getNome();
                else return "vacina";
            }
        });
        
        binder.registerCustomEditor(Patologia.class, "patologia", new PropertyEditorSupport(){
        	@Override
        	public void setAsText(String text) throws IllegalArgumentException {
        		Patologia patologia = animalDAO.buscarPatologiaPorDescricao(text);
        		this.setValue(patologia);
        	}
        	
        	@Override
        	public String getAsText() {
        		Patologia patologia = (Patologia)this.getValue();
        		if (patologia != null) return patologia.getNome();
        		else return "patologia";
        	}
        });
        
        binder.registerCustomEditor(Patologia.class, "patologia", new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Atendimento atendimento = prontuarioDAO.buscarAtendimentoPorPreenchimento(text);
                this.setValue(atendimento);
            }

            @Override
            public String getAsText() {
            	Atendimento atendimento = (Atendimento)this.getValue();
                if (atendimento != null) return atendimento.getTipoDeAtendimento().getNome();
                else return "atendimento";
            }
        });
    }
    
}
