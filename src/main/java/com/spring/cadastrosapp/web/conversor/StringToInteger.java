package com.spring.cadastrosapp.web.conversor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
//String é o dado que vem do formulário e Integer é o dado retornado dessa classe
public class StringToInteger implements Converter<String, Integer>{

	@Override
	public Integer convert(String text) {
		
		//trim é para remover os espaços em branco do início e fim de uma String
		text = text.trim();
		
		//para verificar se a string tem somente dígitos
		if(text.matches("[0-9]+")) {
			return Integer.valueOf(text);
		}
		
		//se ele não tiver somente dígitos, ele irá com o formato nulo para o campo
		//dessa forma, a anotação da classe Endereço (@NotNull) entra em ação
		return null;
	}

}
