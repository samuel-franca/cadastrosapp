package com.spring.cadastrosapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@GetMapping("/cadastrar")
	public String cadastrar() {
		// /departamento para o diretório e /cadastro para a página html
		return "/departamento/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/departamento/lista";
	}

}
