package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Tesis;

public interface TesisRepository extends JpaRepository<Tesis, Integer>{
	
	@Query("select e from Tesis e where (e.titulo like?1) and (?2 is '' or e.tema = ?2) and (?3 is -1 or e.alumno.idAlumno = ?3) and (e.estado = ?4) ")
	public abstract List<Tesis> listaTesisPorTituloTemaAlumno(String titulo,String tema , int idAlumno,int estado );

	@Query("select e from Tesis e where e.titulo like ?1")
    public List<Tesis> listaPorTituloLike(String titulo);
}
