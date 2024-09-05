package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

	//JPQL
	//Query no con tablas sino con clases que tienen @Entity
		
	@Query("select x from Libro x where (x.titulo like ?1) and (?2 is 0 or x.anio=?2)" 
				+ "and (?3 is -1 or x.categoria.idCategoria =?3) and (x.estado = ?4)")       
	public List<Libro> listaLibroPorTituloAnioCategoria(String titulo, int anio, int idCategoria,int estado);
	
	@Query("select x from Libro x where x.titulo like ?1") 
	public List<Libro> listaporTitulolike(String titulo);
	
	
}
