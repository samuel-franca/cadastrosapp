package com.spring.cadastrosapp.domain;

import java.io.Serializable;

import javax.persistence.*;

//necessário porque o eclipse estava dizendo para implementar um ID serial, já que implementamos a interface Serializable. Mas isso não é necessário, porque o JDK já gera um serial quando compilada
@SuppressWarnings("serial")
//para dizer a JPA que essa é uma superclasse das entidades a serem implementadas
@MappedSuperclass
//implementamos nessa classe a interface Serializable, porque segundo o padrão de boas práticas, quando trabalhamos com o framework ORM, devemos transformar as classes em um tipo serializado
//como implementamos essa interface aqui, todas as classes filhas serão serializadas também. Tirando a necessidade de implementar a interface Serializable em cada classe que criaremos no projeto
public abstract class AbstractEntity <ID extends Serializable> implements Serializable { //é abstract para garantir que as classes que queiram utilizar os recursos de AbstractEntity façam apenas por herança e não por instância

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //para gerar o Id automaticamente
	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	//gerado automaticamente: hashCode() and equals()
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		//adicionamos um tipo genérico para o Eclipse não lançar mais o warning
		AbstractEntity<?> other = (AbstractEntity<?>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	//gerado automaticamente
	@Override
	public String toString() {
		return "id=" + id;
	}
		
}
