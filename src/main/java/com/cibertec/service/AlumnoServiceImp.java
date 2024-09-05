package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Alumno;
import com.cibertec.repository.AlumnoRepository;

@Service
public class AlumnoServiceImp implements AlumnoService {

	@Autowired
	private AlumnoRepository Repository;

	@Override
	public List<Alumno> listaTodos() {
		return Repository.findAll();

	}

	@Override
	public Alumno insertaActualizaAlumno(Alumno obj) {
		
		return Repository.save(obj);
	}

	@Override
	public List<Alumno> listaAlumnoPorNombreApellidoPaisEstado(String nombres, String apellidos, int idPais,
			int estado) {
		return Repository.listaAlumnoPorNombreApellidoPaisEstado(nombres, apellidos, idPais, estado);
	}


	@Override
	public List<Alumno> listaAlumnoPorNombreLike(String nombres) {
		
		return Repository.listaPorNombreLike(nombres);
	}
	
	@Override
	public void eliminaAlumno(int idAlumno) {
		Repository.deleteById(idAlumno);
	}


}
