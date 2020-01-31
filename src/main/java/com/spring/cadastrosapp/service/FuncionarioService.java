package com.spring.cadastrosapp.service;

import java.time.LocalDate;
import java.util.List;

import com.spring.cadastrosapp.domain.Funcionario;

public interface FuncionarioService {

    void save(Funcionario funcionario);	

    void delete(Long id);

    Funcionario findById(Long id);
    
    List<Funcionario> findAll();

    List<Funcionario> findByName(String nome);

	List<Funcionario> findByCargo(Long id);

	List<Funcionario> findByDate(LocalDate entrada, LocalDate saida);
	
}
