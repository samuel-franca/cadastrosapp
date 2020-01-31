package com.spring.cadastrosapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.cadastrosapp.domain.Cargo;
import com.spring.cadastrosapp.repository.CargoRepository;

//readonly = false significa que qualquer método, obrigatoriamente, vai precisar abrir uma transação, seja de escrita ou de leitura
//readOnly = false é o padrão. Dessa forma, não seria necessário adicionar o @Transactional(readOnly = false)
@Service @Transactional(readOnly = false)
public class CargoServiceImpl implements CargoService{
	
	@Autowired //para fazer o ponto de injeção do repository. Dessa forma, podemos utilizar as instâncias desse repository (um exemplo é o retorno de findAll)
	private CargoRepository cargoRepository;
	
	//esses métodos foram gerados automaticamente aqui dentro, quando eu cliquei nessa opção ao adicionar o implements CargoService
	
	@Override
	public void save(Cargo cargo) {
		cargoRepository.save(cargo); //essa parte e a dos outros métodos foram modificadas. Nesse caso, modificou-se para salvar o cargo. Quando foi gerado automaticamente, não havia nada
	}

	@Override
	public void delete(Long id) {
		cargoRepository.deleteById(id);
	}
	
	//quando colocamos readonly = true, estamos dizendo que o método é apenas de leitura e não será necessário abrir uma transação
	//quando abrimos uma transação, bloqueamos o acesso a tabela no bd. Caso bloqueássemos a tabela para um método de leitura (consulta), impediríamos que outros usuários façam consultas/update/delete ao mesmo tempo na tabela 
	@Override @Transactional(readOnly = true)
	public Cargo findById(Long id) {
		return cargoRepository.findById(id).get(); //tinha dado erro sem o .get(), porque o findById retorna um Optional e o retorno declarado é Cargo. O .get() retorna um Cargo
	}

	@Override @Transactional(readOnly = true)
	public List<Cargo> findAll() {
		return cargoRepository.findAll();
	}

	@Override
	public boolean hasFuncionarios(Long id) {
		if (findById(id).getFuncionarios().isEmpty()) {
			return false;
		}
		return true;
	}

}