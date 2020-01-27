package com.spring.cadastrosapp.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_cargos")
public class Cargo extends AbstractEntity<Long> {

	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	//porque um departamento poderá ter muitos cargos
	//então a gente sempre lê da direita pra a esquerda
	@ManyToOne
	//nesse anotação, adicionamos o atributo name para indicar o nome da chave estrangeira que teremos na tabela tb_cargos
	@JoinColumn(name = "id_departamento_fk")
	private Departamento departamento;
	
	//lembrando que a classe cargo é o lado fraco do relacionamento
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionarios;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
