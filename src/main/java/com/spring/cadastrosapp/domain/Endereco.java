package com.spring.cadastrosapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_enderecos")
public class Endereco extends AbstractEntity<Long> {
	
	@NotBlank(message = "O logradouro é obrigatório.")
	@Size(min = 3, max = 255, message = "O logradouro deve ter entre {min} e {max} caracteres.")
	@Column(nullable = false)
	private String logradouro;
	
	@NotBlank(message = "O bairro é obrigatório.")
	@Size(min = 3, max = 255, message = "O bairro deve ter entre {min} e {max} caracteres.")
	@Column(nullable = false)
	private String bairro;

	@NotBlank(message = "A cidade é obrigatória.")
	@Size(min = 3, max = 255, message = "A cidade deve ter entre {min} e {max} caracteres.")
	@Column(nullable = false)
	private String cidade;
	
	@NotNull(message = "Selecione uma UF.")
	//o length é 2 porque no bd será salvo apenas a sigla da UF ao salvar um endereço
	@Column(nullable = false, length = 2)
	//@Enumerated vai informar para a JPA qual o tipo de dado a ser salvo no BD, que é String
	@Enumerated(EnumType.STRING)
	//criamos um Enum para representar esse objeto
	private UF uf;
	
	@NotBlank(message = "O CEP é obrigatório.")
	@Size(min = 9, max = 9, message = "O CEP deve conter {max} caracteres.")
	@Column(nullable = false, length = 9)
	private String cep;
	
	@NotNull(message = "O número é obrigatório. Informe 0 para s/n.")
	@Digits(integer = 5, fraction = 0)
	@Column(nullable = false, length = 5)
	private Integer numero;
	
	@Size(max = 255)
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
