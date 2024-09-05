package com.cibertec.service;

import java.util.List;

import com.cibertec.entity.Autor;


public interface AutorService {

	public abstract List<Autor> listaAutor();
	
	public abstract Autor insertaActualizaAutor(Autor obj);
	
	public abstract List<Autor> listaAutorPorNombresTelefonoGrado(String nombres, String telefono, int idGrado, int estado);
	
	public abstract List<Autor> listaAutorPorNombresLike(String nombres);
	
	public abstract void eliminaAutor(int idAutor);
	
	
}
