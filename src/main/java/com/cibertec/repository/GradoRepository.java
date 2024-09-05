package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Grado;

public interface GradoRepository extends JpaRepository<Grado, Integer>{
	 
	
}
	