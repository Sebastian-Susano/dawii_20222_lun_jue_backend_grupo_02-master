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

import com.cibertec.entity.Alumno;
import com.cibertec.service.AlumnoService;
import com.cibertec.util.AppSettings;
import com.cibertec.util.Constantes;

@RestController
@RequestMapping("/url/alumno")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Alumno>> listaTodos(){
		List<Alumno> lista = alumnoService.listaTodos();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> inserta(@Valid @RequestBody Alumno obj, Errors errors){
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
			try {
			obj.setIdAlumno(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);			
			Alumno objSalida = alumnoService.insertaActualizaAlumno(obj);
			if (objSalida == null) {
				lstMensajes.add("Error en el registro");
			}else {
				lstMensajes.add("Se registró el alumno con el ID ==> " + objSalida.getIdAlumno());
			}
		}catch (Exception e) {
			salida.put("errores", lstMensajes);
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}

	
	@GetMapping("/listaAlumnoConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaAlumnoPorNombreApellidoPaisEstado(
			@RequestParam(name = "nombres", required = false, defaultValue = "") String nombres,
			@RequestParam(name = "apellidos", required = false, defaultValue = "") String apellidos,
			@RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Alumno> lista = alumnoService.listaAlumnoPorNombreApellidoPaisEstado("%"+nombres+"%", apellidos, idPais, estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", alumnoService.insertaActualizaAlumno(null));
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
@GetMapping("/listaAlumnoPorNombreLike/{nom}")
@ResponseBody
public ResponseEntity<List<Alumno>> listaAlumnoPorNombreLike(@PathVariable("nom") String nom) {
	List<Alumno> lista = null;
	try {
		if (nom.equals("todos")) {
			lista = alumnoService.listaAlumnoPorNombreLike("%");
		} else {
			lista = alumnoService.listaAlumnoPorNombreLike("%" + nom + "%");
		}
		System.out.print(lista);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return ResponseEntity.ok(lista);
}

@PostMapping("/registraAlumno")
@ResponseBody
public ResponseEntity<Map<String, Object>> insertaAlumno(@RequestBody Alumno obj) {
	Map<String, Object> salida = new HashMap<>();
	try {
		obj.setIdAlumno(0);
		obj.setFechaRegistro(new Date());
		obj.setEstado(1);
		Alumno objSalida = alumnoService.insertaActualizaAlumno(obj);
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

  
@PutMapping("/actualizaAlumno")
@ResponseBody
public ResponseEntity<Map<String, Object>> actualizaAlumno(@RequestBody Alumno obj) {
	Map<String, Object> salida = new HashMap<>();
	try {
		Alumno objSalida = alumnoService.insertaActualizaAlumno(obj);
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



@DeleteMapping("/eliminaAlumno/{id}")
@ResponseBody
public ResponseEntity<Map<String, Object>> eliminaAlumno(@PathVariable("id") int idAlumno) {
	Map<String, Object> salida = new HashMap<>();
	try {
		alumnoService.eliminaAlumno(idAlumno);
		salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
	} catch (Exception e) {
		e.printStackTrace();
		salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
	}
	return ResponseEntity.ok(salida);
}

	
	
	
}
