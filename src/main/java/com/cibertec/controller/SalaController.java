package com.cibertec.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.entity.Sala;
import com.cibertec.service.SalaService;
import com.cibertec.util.AppSettings;
import com.cibertec.util.Constantes;

/*
 * Autor : Jacqueline Vanessa Flores Hurtado
 */

@RestController
@RequestMapping("/url/sala")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class SalaController {

	@Autowired
	private SalaService salaService;

	/*
	 * Listado de Sala - Jacqueline Flores
	 */
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Sala>> listaSala() {

		List<Sala> lista = salaService.listaSala();
		return ResponseEntity.ok(lista);

	}

	/*
	 * Inserta datos en sala - Jacqueline Flores
	 */

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> insertaSala(@Valid @RequestBody Sala obj, Errors erros) {
		Map<String, Object> salida = new HashMap<>();
		List<String> lstMensajes = new ArrayList<>();
		salida.put("sala", lstMensajes);

		List<ObjectError> lstErros = erros.getAllErrors();
		for (ObjectError objectError : lstErros) {
			objectError.getDefaultMessage();
			lstMensajes.add(objectError.getDefaultMessage());
		}
		if (!CollectionUtils.isEmpty(lstMensajes)) {
			return ResponseEntity.ok(salida);

		}
		obj.setFechaRegistro(new Date());
		Sala objSalida = salaService.insertaActualizaSala(obj);
		if (objSalida == null) {
			lstMensajes.add("Error al Registrar Sala");
		} else {

			lstMensajes.add("Se Registro Correctamente la Sala de ID => " + objSalida.getIdSala());
		}

		return ResponseEntity.ok(salida);
	}

	@GetMapping("/listaSalaConParametros")

	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaSalaNumeroFechaSede(

			@RequestParam(name = "numero", required = false, defaultValue = "") String numero,

			@RequestParam(name = "numAlumnos", required = false, defaultValue = "0") int numAlumnos,

			@RequestParam(name = "idSede", required = false, defaultValue = "-1") int idSede,

			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Sala> lista = salaService.listaSalaNumeroFechaSede("%" + numero + "%", numAlumnos, idSede, estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			} else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	// Lista de todos , pero filtra por el numero de sala - Jacqueline Flores

	@GetMapping("/listaSalaPorNumeroLike/{num}")
	@ResponseBody
	public ResponseEntity<List<Sala>> listaSalaPorNumeroLike(@PathVariable("num") String num) {
		List<Sala> lista = null;
		try {
			if (num.equals("todos")) {
				lista = salaService.listaSalaPorNumeroLike("%");
			} else {
				lista = salaService.listaSalaPorNumeroLike("%" + num + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}

	// Registra Sala - Jacqueline Flores
	@PostMapping("/registraSala")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaSala(@RequestBody Sala obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdSala(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Sala objSalida = salaService.insertaActualizaSala(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	// Actualiza Sala - Jacqueline Flores
	@PutMapping("/actualizaSala")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaSala(@RequestBody Sala obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Sala objSalida = salaService.insertaActualizaSala(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_ACT_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	// Elimina Sala - Jacqueline Flores

	@DeleteMapping("/eliminaSala/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaSala(@PathVariable("id") int idSala) {
		Map<String, Object> salida = new HashMap<>();
		try {
			salaService.eliminaSala(idSala);
			salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

}
