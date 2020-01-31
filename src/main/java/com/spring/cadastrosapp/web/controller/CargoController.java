package com.spring.cadastrosapp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.cadastrosapp.domain.Cargo;
import com.spring.cadastrosapp.domain.Departamento;
import com.spring.cadastrosapp.service.CargoService;
import com.spring.cadastrosapp.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		// /cargo para o diretório e /cadastro para a página html
		return "/cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos", cargoService.findAll());
		return "/cargo/lista";
	}

	@PostMapping("/salvar")
	public String salvar(Cargo cargo, RedirectAttributes attr) {
		cargoService.save(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	//posibilita listar no combobox todos os departamentos que temos cadastrados no bd
	@ModelAttribute("departamentos")
	//ModelMap permite enviar os departamentos como uma variável para a página de cadastro
	public List<Departamento> listaDeDepartamentos() {
		return departamentoService.findAll();
	}
	
	@GetMapping("/editar/{id}")
	//o @PathVariable consegue recuperar para gente o id enviado no path
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cargo", cargoService.findById(id));
		return "/cargo/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar (Cargo cargo, RedirectAttributes attr) {
		//quando o registro já possui um ID, o spring faz o update ao invés de um insert
		cargoService.save(cargo);
		attr.addFlashAttribute("success", "Cargo editado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		//criou-se uma regra de negócio que não permita excluir um cargo, se existir pelo menos um funcionario vinculado a este cargo
		//a regra foi declarada no CargoService e implementada no CargoServiceImpl
		if(cargoService.hasFuncionarios(id)) {
			attr.addFlashAttribute("fail", "Cargo não removido. Possui funcionário(s) vinculado(s).");
		} else {
			cargoService.delete(id);
			attr.addFlashAttribute("success", "Cargo removido com sucesso.");
		}
		return "redirect:/cargos/listar";
	}
	
}
