package com.spring.cadastrosapp.service;

import java.util.List;

import com.spring.cadastrosapp.domain.Funcionario;

public interface FuncionarioService {

    void save(Funcionario funcionario);	

    void delete(Long id);

    Funcionario findById(Long id);
    
    List<Funcionario> findAll();
	
}
