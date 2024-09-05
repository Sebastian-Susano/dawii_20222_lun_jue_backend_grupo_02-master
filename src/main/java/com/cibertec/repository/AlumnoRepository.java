package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{
	@Query("select x from Alumno  x where (x.nombres like ?1) and (?2 is '' or x.apellidos = ?2) and (?3 is -1 or x.pais.idPais = ?3) and (x.estado = ?4) ")
	public abstract List<Alumno> listaAlumnoPorNombreApellidoPaisEstado(String nombres, String apellidos, int idPais,int estado);

	@Query("select x from Alumno x where x.nombres like ?1")
	public List<Alumno> listaPorNombreLike(String nombres);

}