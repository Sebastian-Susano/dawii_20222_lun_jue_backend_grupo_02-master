package com.cibertec.service;

import java.util.List;

import com.cibertec.entity.Sala;

public interface SalaService {

	public List<Sala> listaSala();

	public abstract List<Sala> listaSalaNumeroFechaSede(String numero, int numAlumnos, int idSede, int estado);

	public Sala insertaActualizaSala(Sala objsala);

	public abstract List<Sala> listaSalaPorNumeroLike(String numero);

	public abstract void eliminaSala(int idSala);

}
