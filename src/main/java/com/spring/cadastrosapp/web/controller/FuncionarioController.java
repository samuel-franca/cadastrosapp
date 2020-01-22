package com.spring.cadastrosapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@GetMapping("/cadastrar")
	public String cadastrar() {
		// /funcionario para o diretório e /cadastro para a página html
		return "/funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/funcionario/lista";
	}

}
