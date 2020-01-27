package com.spring.cadastrosapp.domain;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_enderecos")
public class Endereco extends AbstractEntity<Long> {

	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = false)
	private String bairro;
	
	@Column(nullable = false)
	private String cidade;
	
	//o length é 2 porque no bd será salvo apenas a sigla da UF ao salvar um endereço
	@Column(nullable = false, length = 2)
	//@Enumerated vai informar para a JPA qual o tipo de dado a ser salvo no BD, que é String
	@Enumerated(EnumType.STRING)
	//criamos um Enum para representar esse objeto
	private UF uf;
	
	@Column(nullable = false, length = 9)
	private String cep;
	
	@Column(nullable = false, length = 5)
	private Integer numero;
	
	//não foi adicionado nada no @Column porque ele não é um campo obrigatório (então não coloca nullable = false) e o tamanho dele será o padrão (255 caracteres)
	private String complemento;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
		
}
