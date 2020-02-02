package com.spring.cadastrosapp.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_funcionarios")
public class Funcionario extends AbstractEntity<Long> {

	@NotBlank(message = "O nome do funcionário é obrigatório.")
	@Size(min = 3, max = 255, message = "O nome do funcionário deve ter entre {min} e {max} caracteres.")
	@Column(nullable = false, unique = true)
	private String nome;
	
	@NotNull(message = "O salário do funcionário é obrigatório.")
	//a anotação @NumberFormat faz a conversão do salário
	//nessa anotação, devemos informar qual o estilo de dados. O CURRENCY é o tipo moeda para o campo salário
	//e informamos também o padrão de dados que vai ser utilizado. Observe que é o padrão americano. Isso é necessário porque devemos salvar no bd como padrão americano
	//dessa forma, ele converte o valor recebido (que está no padrão brasileiro) para o padrão americano
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	//columnDefinition serve para definir o tipo de dado que teremos no BD
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00") //vamos ter um tamanho de 7 dígitos, com duas casas depois da vírgula
	private BigDecimal salario;
	
	@NotNull(message = "A data de entrada do funcionário é obrigatória.")
	@PastOrPresent(message = "A data deve ser igual ou anterior a atual.")
	//a anotação @DateTimeFormat tem o atributo iso, no qual informamos qual o tipo de data que iremos trabalhar (no nosso caso, somente DATE)
	//poderia ser, por exemplo, data e hora ou somente a hora
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_entrada", nullable = false, columnDefinition = "DATE")
	private LocalDate dataEntrada;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_saida", columnDefinition = "DATE")
	private LocalDate dataSaida;
	
	//quando utilizamos @Valid sobre o objeto endereço, estamos dizendo que o objeto endereço deve ser validado, mas deve ser validado conforme as instruções de validação que temos na classe de endereço
	@Valid
	//colocamos o cascade como ALL porque quando formos inserir um funcionário, inseriremos também por cascata o endereço
	//além disso, quando excluirmos um funcionario, queremos que o endereço também seja excluído do bd
	@OneToOne(cascade = CascadeType.ALL)
	//JoinColumn para nomear a chave estrangeira na tb_funcionarios
	@JoinColumn(name = "endereco_id_fk")
	private Endereco endereco;
	
	@NotNull(message = "Selecione um cargo.")
	@ManyToOne
	@JoinColumn(name = "cargo_id_fk")
	private Cargo cargo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
}