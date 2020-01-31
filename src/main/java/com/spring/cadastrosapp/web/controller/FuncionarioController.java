package com.spring.cadastrosapp.web.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.cadastrosapp.domain.Cargo;
import com.spring.cadastrosapp.domain.Funcionario;
import com.spring.cadastrosapp.domain.UF;
import com.spring.cadastrosapp.service.CargoService;
import com.spring.cadastrosapp.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private CargoService cargoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		// /funcionario para o diretório e /cadastro para a página html
		return "/funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.findAll());
		return "/funcionario/lista";
	}

	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.save(funcionario);
		attr.addFlashAttribute("success", "Funcionário cadastrado com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	//posibilita listar no combobox todos os funcionarios que temos cadastrados no bd
	@ModelAttribute("cargos")
	public List<Cargo> listaDeCargos() {
		return cargoService.findAll();
	}
	
	//posibilita listar no combobox as UFs que temos cadastrados no bd
	@ModelAttribute("ufs")
	public UF[] listaDeUFs() {
		//values() é um método do Enum, que retorna todas as constantes que temos no Enum em forma de array
		return UF.values();
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.findById(id));
		return "funcionario/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.save(funcionario);
		attr.addFlashAttribute("success", "Funcionário editado com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}	
	
	//diferentemente de Cargo e Departamento, não há necessidade de verificar se o funcionário tem vínculos, então a exclusão pode ser direta sem testar
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		funcionarioService.delete(id);
		attr.addFlashAttribute("success", "Funcionário removido com sucesso.");
		return "redirect:/funcionarios/listar";
	}
	
	//método responsável por receber a requisição do formulário da consulta pelo nome
	@GetMapping("/buscar/nome")
	//utilizamos o @RequestParam ao invés do @PathVariable porque o valor do nome não vai chegar pela URL, mas sim como um atributo (parâmetro) do request
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.findByName(nome));
		return "/funcionario/lista";
	}
	
	@GetMapping("/buscar/cargo")
	public String getPorCargo(@RequestParam("id") Long id, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.findByCargo(id));
		return "/funcionario/lista";
	}
	
	@GetMapping("/buscar/data")
	public String getPorDatas(@RequestParam("entrada") @DateTimeFormat(iso = ISO.DATE) LocalDate entrada,
							  @RequestParam("saida") @DateTimeFormat(iso = ISO.DATE) LocalDate saida, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.findByDate(entrada, saida));
		return "/funcionario/lista";
	}
	
}
