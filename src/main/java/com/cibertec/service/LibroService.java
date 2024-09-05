package com.cibertec.service;

import java.util.List;

import com.cibertec.entity.Libro;

public interface LibroService {
		
	public abstract List<Libro> listaLibro();
	public abstract Libro insertaActualizaLibro(Libro obj);
	public abstract List<Libro> listaLibroPorTituloAnioCategoria(String titulo, int anio, int idCategoria,int estado);
	public abstract List<Libro> listaLibroPorTituloLike(String titulo);
	public abstract void eliminarLibro(int idLibro);
}
