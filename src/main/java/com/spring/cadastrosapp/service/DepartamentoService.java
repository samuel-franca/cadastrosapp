package com.spring.cadastrosapp.service;

import java.util.List;

import com.spring.cadastrosapp.domain.Departamento;

public interface DepartamentoService {

    void save(Departamento departamento);	

    void delete(Long id);

    Departamento findById(Long id);
    
    List<Departamento> findAll();	

}