package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer>{
	
	//JPQL
		//Query no con tablas sino con clases que tienen @Entity
		
		@Query("select x from Autor x where (x.nombres like ?1) and (?2 is '' or x.telefono=?2) and (?3 is -1 or x.grado.idGrado = ?3) and (x.estado = ?4) ")         
		public List<Autor> listaAutorPorNombresTelefonoGrado(String nombres, String tenefono, int idGrado, int estado);

		@Query("select x from Autor x where x.nombres like ?1")
		public List <Autor> listaPorNombresLike(String nombres);

		
}
