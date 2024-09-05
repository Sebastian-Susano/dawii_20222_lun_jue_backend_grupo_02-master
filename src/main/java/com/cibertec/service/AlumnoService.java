package com.cibertec.service;

import java.util.List;

import com.cibertec.entity.Alumno;

public interface AlumnoService {

	public abstract List<Alumno> listaTodos();
	public abstract Alumno insertaActualizaAlumno(Alumno obj);
	public abstract List<Alumno> listaAlumnoPorNombreApellidoPaisEstado(String nombres, String apellidos, int idPais,
			int estado);

	public abstract List<Alumno> listaAlumnoPorNombreLike(String nombres);
	public abstract void eliminaAlumno(int idAlumno);


}
