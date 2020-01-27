package com.spring.cadastrosapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.cadastrosapp.domain.Funcionario;
import com.spring.cadastrosapp.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{

	@Autowired
	private FuncionarioRepository funcionarioRepository;
		
	@Override
	public void save(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);		
	}

	@Override
	public void delete(Long id) {
		funcionarioRepository.deleteById(id);		
	}

	@Override @Transactional(readOnly = true)
	public Funcionario findById(Long id) {
		return funcionarioRepository.findById(id).get();
	}

	@Override @Transactional(readOnly = true)
	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}	
	
}
