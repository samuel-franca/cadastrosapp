package com.spring.cadastrosapp.web.validator;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.cadastrosapp.domain.Funcionario;

public class FuncionarioValidator implements Validator {

	//o método supports tem como princípio validar o objeto que enviamos a partir do formulário, com o objeto que a classe realmente deve validar
	//esse teste faz a validação pra gente
	//clazz é o objeto que nós temos no formulário, que é do tipo do funcionário
	@Override
	public boolean supports(Class<?> clazz) {
		//o clazz é funcionario e se a classe que a gente tá esperando é também é funcionário, ele retorna true e o processo vai para o método validate
		return Funcionario.class.equals(clazz);
	}

	//Object é o objeto que estamos recebendo do formulário (objeto de funcionário)
	//Errors é o objeto no qual iremos lidar com a validação
	@Override
	//todos os campos do formulário poderiam ser validados através desta classe, basta criar as regras e, na variável errors, colocamos o nome do campo e a mensagem que queremos caso não passe na regra
	public void validate(Object object, Errors errors) {
		Funcionario f = (Funcionario) object;
		
		LocalDate entrada = f.getDataEntrada();
		
		if(f.getDataSaida() != null) {
			//o isBefore, que é do próprio LocalDate, testa se o valor um valor (no nosso caso, a DataSaida) é anterior ao valor da outra data (entrada)
			if (f.getDataSaida().isBefore(entrada)) {
				//a mensagem de erro (segundo argumento) está em messages.properties
				errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
			}
		}

	}

}