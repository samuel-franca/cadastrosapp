package com.spring.cadastrosapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.cadastrosapp.domain.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>{

}
