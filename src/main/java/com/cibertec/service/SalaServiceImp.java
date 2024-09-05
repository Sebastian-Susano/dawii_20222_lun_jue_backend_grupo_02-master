package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Sala;
import com.cibertec.repository.SalaRepository;

@Service
public class SalaServiceImp implements SalaService {

	@Autowired
	private SalaRepository reposala;

	@Override
	public List<Sala> listaSala() {
		return reposala.findAll();
	}

	@Override
	public Sala insertaActualizaSala(Sala objsala) {
		return reposala.save(objsala);
	}

	@Override
	public List<Sala> listaSalaNumeroFechaSede(String numero, int numAlumnos, int idSede, int estado) {

		return reposala.listaSalaPorNumeroFechaSedeEstado(numero, numAlumnos, idSede, estado);
	}

	@Override
	public List<Sala> listaSalaPorNumeroLike(String numero) {

		return reposala.listaPorNumeroLike(numero);
	}

	@Override
	public void eliminaSala(int idSala) {

		reposala.deleteById(idSala);
	}

}
