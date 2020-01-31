package com.spring.cadastrosapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.cadastrosapp.domain.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

    @Query(value = "select * from tb_funcionarios where nome like concat('%',?1,'%')", nativeQuery = true)
    public List<Funcionario> findByNome(String nome);

    @Query(value = "select * from tb_funcionarios where cargo_id_fk = ?1", nativeQuery = true)
	public List<Funcionario> findByCargo(Long id);

    @Query(value = "select * from tb_funcionarios where data_entrada >= ?1 and data_saida <= ?2 order by data_entrada asc", nativeQuery = true)
	public List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida);

    @Query(value = "select * from tb_funcionarios where data_entrada = ?1 order by data_entrada asc", nativeQuery = true)
	public List<Funcionario> findByDataEntrada(LocalDate entrada);

    @Query(value = "select * from tb_funcionarios where data_saida = ?1 order by data_entrada asc", nativeQuery = true)
	public List<Funcionario> findByDataSaida(LocalDate saida);

}