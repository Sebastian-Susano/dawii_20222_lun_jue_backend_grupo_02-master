package com.cibertec.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Tesis;
import com.cibertec.repository.TesisRepository;

@Service
public class TesisServiceImp implements TesisService{

	@Autowired
	private TesisRepository repository;
	
	@Override
	public Tesis insertaActualizaTesis(Tesis obj) {
		return repository.save(obj);
	}

	@Override
	public List<Tesis> listaTesis() {
		return repository.findAll();
	}

	
	@Override
	public List<Tesis> listaTesisPorTituloTemaAlumno(String titulo, String tema, int idAlumno, int estado) {
		// TODO Auto-generated method stub
		return repository.listaTesisPorTituloTemaAlumno(titulo,tema,idAlumno,estado);
	}

	@Override
	public List<Tesis> listaTesisPorTituloLike(String titulo) {
		// TODO Auto-generated method stub
		return repository.listaPorTituloLike(titulo);
	}

	@Override
	public void eliminarTesis(int idTesis) {
		// TODO Auto-generated method stub
		repository.deleteById(idTesis);
	}
	
	
	

}
