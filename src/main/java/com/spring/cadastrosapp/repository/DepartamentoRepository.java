package com.spring.cadastrosapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.cadastrosapp.domain.Departamento;

@Repository
//vai ter a entidade Departamento e como chave, um valor do tipo Long
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{
	

}
