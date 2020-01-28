package com.spring.cadastrosapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.cadastrosapp.domain.Departamento;
import com.spring.cadastrosapp.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/cadastrar")
	//adicionou-se na assinatura do método o objeto departamento porque havia dado um erro quando o Thymeleaf tentava abrir a página cadastro.html
	//porque o controller não estava enviando um objeto departamento para a página
	public String cadastrar(Departamento departamento) {
		// /departamento para o diretório e /cadastro para a página html
		return "/departamento/cadastro";
	}
	
	@GetMapping("/listar")
	//ModelMap permite enviar os departamentos como uma variável para a página de cadastro
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", departamentoService.findAll());
		return "/departamento/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Departamento departamento) {
		departamentoService.save(departamento);
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	//o @PathVariable consegue recuperar para gente o id enviado no path
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("departamento", departamentoService.findById(id));
		return "/departamento/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar (Departamento departamento) {
		//eu utilizei o save porque não há o update no JPARepository
		//quando o registro já possui um ID, o spring faz o update ao invés de um insert, por isso não haveria a necessidade de ser um update
		departamentoService.save(departamento);
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		//criou-se uma regra de negócio que não permita excluir um departamento, se existir pelo menos um cargo vinculado a este departamento
		//a regra foi declarada no DepartamentoService e implementada no DepartamentoServiceImpl
		if(!departamentoService.hasCargos(id)) {
			departamentoService.delete(id);
		}
		//apenas chamamos o método listar passando o model, ao invés de redirecionar. Esse método é similar ao de redirecionar
		//também poderíamos ter redirecionado para lista.html
		return listar(model);
	}
	
}
