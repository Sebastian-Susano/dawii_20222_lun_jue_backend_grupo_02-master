package com.cibertec.service;


import java.util.List;

import com.cibertec.entity.Tesis;

public interface TesisService {

	public abstract List<Tesis> listaTesis();
	public abstract Tesis insertaActualizaTesis(Tesis obj);
	public abstract List<Tesis> listaTesisPorTituloTemaAlumno(String titulo,String tema, int idAlumno,int estado );
    public abstract List<Tesis> listaTesisPorTituloLike(String titulo);
    public abstract void eliminarTesis(int idTesis);
}
