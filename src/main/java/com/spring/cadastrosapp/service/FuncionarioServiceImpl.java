package com.spring.cadastrosapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
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

	@Override @Transactional(readOnly = true)
	public List<Funcionario> findByName(String nome) {
		return funcionarioRepository.findByNome(nome);
	}

	@Override
	public List<Funcionario> findByCargo(Long id) {
		return funcionarioRepository.findByCargo(id);
	}

	@Override
	public List<Funcionario> findByDate(LocalDate entrada, LocalDate saida) {
	    if (entrada != null && saida != null) {	    	
	        return funcionarioRepository.findByDataEntradaDataSaida(entrada, saida);
	    } else if (entrada != null) {        	
	        return funcionarioRepository.findByDataEntrada(entrada);
	    } else if (saida != null) {        	
	        return funcionarioRepository.findByDataSaida(saida);
	    //condição se o usuário não selecionou nenhuma data e clicou no botão
	    } else {
	    	return new ArrayList<>();
	    }
	}
	
}
