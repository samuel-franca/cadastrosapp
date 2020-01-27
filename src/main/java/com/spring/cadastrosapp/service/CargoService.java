package com.spring.cadastrosapp.service;

import java.util.List;

import com.spring.cadastrosapp.domain.Cargo;

public interface CargoService {

    void save(Cargo cargo);	

    void delete(Long id);

    Cargo findById(Long id);
    
    List<Cargo> findAll();	
	
}