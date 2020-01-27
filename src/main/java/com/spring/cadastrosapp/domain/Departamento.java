package com.spring.cadastrosapp.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_departamentos")
//definimos o tipo genérico como Long. Então o Id dessa classe vai ser herdado como um tipo Long
public class Departamento extends AbstractEntity<Long>{
	
	//inserimos apenas nome, porque o Id vai ser herdado a partir de AbstractEntity
	
	//quando o nome da coluna e o nome do atributo são os mesmos, não há necesidade de adicionar o atributo name
	//ou seja, em @Column poderíamos ter apenas o nullable, unique e length
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;

	//é do tipo List para cargos porque Departamento vai ter uma lista de cargos
	//ou seja, temos muitos cargos para um departamento
	@OneToMany(mappedBy = "departamento") /*mappedBy é necessário porque o relacionamento entre Departamento e Cargo será bidirecional e, quando há esse tipo de relacionamento,
											obrigatoriamento deveremos definir o lado fraco e lado forte o lado forte é o que contém a chave estrangeira, ou seja, cargo.
											Então o mappedBy diz qual atributo que tá fazendo parte do lado forte da relação. Esse atributo é o departamento, na classe cargo.
											Dessa forma, o departamento é o lado fraco da relação. E o lado forte é o cargo*/
	private List<Cargo> cargos;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

}
