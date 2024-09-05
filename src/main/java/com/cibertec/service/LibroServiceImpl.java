package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Libro;
import com.cibertec.repository.LibroRepository;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroRepository repository;
	
	@Override
	public Libro insertaActualizaLibro(Libro obj) {
		return repository.save(obj);
	}
	
	@Override
	public List<Libro> listaLibro() {
		return repository.findAll();
	}
	
	@Override
	public List<Libro> listaLibroPorTituloAnioCategoria(String titulo, int anio, int idCategoria,int estado) {
		return repository.listaLibroPorTituloAnioCategoria(titulo, anio, idCategoria, estado);
	}

	@Override
	public List<Libro> listaLibroPorTituloLike(String titulo) {
		// TODO Auto-generated method stub
		return repository.listaporTitulolike(titulo);
		
	}

	@Override
	public void eliminarLibro(int idLibro) {
		// TODO Auto-generated method stub
		repository.deleteById(idLibro);
		
	}
	
	
}
