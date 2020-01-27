package com.spring.cadastrosapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.cadastrosapp.domain.Departamento;
import com.spring.cadastrosapp.repository.DepartamentoRepository;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Override
	public void save(Departamento departamento) {
		departamentoRepository.save(departamento);
	}

	@Override
	public void delete(Long id) {
		departamentoRepository.deleteById(id);
	}

	@Override @Transactional(readOnly = true)
	public Departamento findById(Long id) {
		return departamentoRepository.findById(id).get();
	}

	@Override @Transactional(readOnly = true)
	public List<Departamento> findAll() {
		return departamentoRepository.findAll();
	}

}
