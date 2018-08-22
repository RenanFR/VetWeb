package com.vetweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vetweb.dao.DocumentoDAO;
import com.vetweb.model.ModeloDocumento;

@Controller
@Transactional
@RequestMapping("documentos")
public class DocumentoController {
	
	@Autowired
	private DocumentoDAO documentoDAO;
	
	@GetMapping("form")
	public ModelAndView formModeloDocumento(@ModelAttribute("modeloDocumento") ModeloDocumento modeloDocumento) {
		ModelAndView modelAndView = new ModelAndView("documentos/cadastroModeloDocumento");
		return modelAndView;
	}
	
	@PostMapping("submitForm")
	public ModelAndView submitForm(@ModelAttribute("modeloDocumento") ModeloDocumento modeloDocumento) {
		ModelAndView modelAndView = new ModelAndView("redirect:/documentos/modelos");
		documentoDAO.salvarModeloDocumento(modeloDocumento);
		return modelAndView;
	}
	
	@GetMapping("modelos")
	public ModelAndView modelos() {
		ModelAndView modelAndView = new ModelAndView("documentos/modelos");
		modelAndView.addObject("modelos", documentoDAO.listarTodosModelos());
		return modelAndView;
	}
	
	@GetMapping("atualizarModelo/{modelodocumentoId}")
	public ModelAndView atualizarModelo(@PathVariable("modelodocumentoId") Long modeloId) {
		ModelAndView modelAndView = new ModelAndView("documentos/cadastroModeloDocumento");
		ModeloDocumento modeloDocumento = documentoDAO.buscarModeloPorId(modeloId);
		modelAndView.addObject("modeloDocumento", modeloDocumento);
		return modelAndView;
	}
	
	@GetMapping("removerModelo/{modelodocumentoId}")
	public ModelAndView removerModelo(@PathVariable("modelodocumentoId") Long modeloId) {
		ModelAndView modelAndView = new ModelAndView("redirect:/documentos/modelos");
		ModeloDocumento modeloDocumento = documentoDAO.buscarModeloPorId(modeloId);
		documentoDAO.removerModelo(modeloDocumento);
		return modelAndView;
	}

}
