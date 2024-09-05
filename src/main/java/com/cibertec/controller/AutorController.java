package com.cibertec.controller;

/**
 * @author Alejandra Price
 */
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

import com.cibertec.entity.Autor;
import com.cibertec.service.AutorService;
import com.cibertec.util.Constantes;
import com.cibertec.util.AppSettings;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/url/autor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AutorController {

	@Autowired
	private AutorService autorService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Autor>> listaAutor(){
		List<Autor> lista = autorService.listaAutor();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> inserta(@Valid @RequestBody Autor obj, Errors errors){
		HashMap<String, Object> salida = new HashMap<>();
		List<String> lstMensajes = new ArrayList<String>();
		salida.put("errores", lstMensajes);
		
		List<ObjectError> lstErrors =  errors.getAllErrors();
		for (ObjectError objectError : lstErrors) {
			objectError.getDefaultMessage();
			lstMensajes.add(objectError.getDefaultMessage());
		}

		if (!CollectionUtils.isEmpty(lstMensajes)) {
			return ResponseEntity.ok(salida);
		}
		
		obj.setFechaRegistro(new Date());
		obj.setEstado(1);
		Autor objSalida = autorService.insertaActualizaAutor(obj);
		
		if (objSalida == null) {
			lstMensajes.add("Error en el registro");
		}else {
			lstMensajes.add("Se registró el Autor con el ID ==> " + objSalida.getIdAutor());
		}
		return ResponseEntity.ok(salida);
	}
	@GetMapping("/listaAutorConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaAutorPorNombresTelefonoGrado(
			@RequestParam(name = "nombres", required = false, defaultValue = "") String nombres,
			@RequestParam(name = "telefono", required = false, defaultValue = "") String telefono,
			@RequestParam(name = "idGrado", required = false, defaultValue = "-1") int idGrado,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Autor> lista = autorService.listaAutorPorNombresTelefonoGrado("%"+nombres+"%", telefono, idGrado, estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
		@GetMapping("/listaAutorPorNombresLike/{nom}")
		@ResponseBody
		public ResponseEntity<List<Autor>> listaSalaPorNumeroLike(@PathVariable("nom") String nom) {
			List<Autor> lista = null;
			try {
				if (nom.equals("todos")) {
					lista = autorService.listaAutorPorNombresLike("%");
				} else {
					lista = autorService.listaAutorPorNombresLike("%" + nom + "%");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ResponseEntity.ok(lista);
		}
		
		@PostMapping("/registraAutor")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> insertaAutor(@RequestBody Autor obj){
			Map<String, Object> salida = new HashMap<>();
			try {
				obj.setIdAutor(0);
				obj.setFechaRegistro(new Date());
				obj.setEstado(1);
				Autor objSalida = autorService.insertaActualizaAutor(obj);
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
		
		
			@PutMapping("/actualizaAutor")
			@ResponseBody
			public ResponseEntity<Map<String, Object>> actualizaAutor(@RequestBody Autor obj) {
				Map<String, Object> salida = new HashMap<>();
				try {
					Autor objSalida = autorService.insertaActualizaAutor(obj);
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


			@DeleteMapping("/eliminaAutor/{id}")
			@ResponseBody
			public ResponseEntity<Map<String, Object>> eliminaAutor(@PathVariable("id") int idAutor) {
				Map<String, Object> salida = new HashMap<>();
				try {
					autorService.eliminaAutor(idAutor);
					salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
				} catch (Exception e) {
					e.printStackTrace();
					salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
				}
				return ResponseEntity.ok(salida);
			}

}


