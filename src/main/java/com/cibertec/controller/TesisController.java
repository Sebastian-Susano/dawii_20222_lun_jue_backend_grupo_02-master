package com.cibertec.controller;
/**
 * @author Sebas
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

import com.cibertec.entity.Tesis;
import com.cibertec.service.TesisService;





@RestController
@RequestMapping("/url/tesis")
@CrossOrigin(origins = "http://localhost:4200")
public class TesisController {

	@Autowired
	private TesisService tesisService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Tesis>> listaAlumno(){
		List<Tesis> lista = tesisService.listaTesis();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> inserta(@Valid @RequestBody Tesis obj,Errors errors){
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
			obj.setIdTesis(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Tesis objSalida = tesisService.insertaActualizaTesis(obj);
			if (objSalida == null) {
				lstMensajes.add("Error en el registro");
			}else {
				lstMensajes.add("Se registró la Tesis con el ID ==> " + objSalida.getIdTesis());
			}
		
		
	}catch(Exception e) {
		salida.put("errores",lstMensajes);
		e.printStackTrace();
	}
		return ResponseEntity.ok(salida);
	}
	
		@GetMapping("/listaTesisConParametros")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> listaTesisTituloTemaAlumno(
				@RequestParam(name = "titulo", required = false,defaultValue = "")String titulo,
				@RequestParam(name = "tema", required = false,defaultValue = "")String tema,
				@RequestParam(name = "idAlumno", required = false,defaultValue = "-1")int idAlumno,
				@RequestParam(name = "estado", required = false,defaultValue = "1")int estado){
			Map<String,Object> salida = new HashMap<>();
			try {
				List<Tesis> lista = tesisService.listaTesisPorTituloTemaAlumno("%"+titulo+"%", tema, idAlumno,estado);
				if(CollectionUtils.isEmpty(lista)) {
					salida.put("mensaje", "No existen datos para mostrar");
				}else {
					salida.put("lista", lista);
					salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
				}
			}catch(Exception e) {
				e.printStackTrace();
				
			}
		return ResponseEntity.ok(salida);
		}
		
		@GetMapping("listaTesisPorTituloLike/{titu}")
		@ResponseBody
		public ResponseEntity<List<Tesis>> listaTesisPorTituloLike(@PathVariable("titu")String titu){
			List<Tesis> lista = null;
			try {
				if(titu.equals("todos")) {
					lista = tesisService.listaTesisPorTituloLike("%");
				}else {
					lista = tesisService.listaTesisPorTituloLike("%" + titu + "%");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return ResponseEntity.ok(lista);
		}

		@PostMapping("/registraTesis")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> insertaTesis(@RequestBody Tesis obj) {
			Map<String, Object> salida = new HashMap<>();
			try {
				obj.setIdTesis(0);
				obj.setFechaRegistro(new Date());
				obj.setEstado(1);
				Tesis objSalida =  tesisService.insertaActualizaTesis(obj);
				if (objSalida == null) {
					salida.put("mensaje", com.cibertec.util.Constantes.MENSAJE_REG_ERROR);
				} else {
					salida.put("mensaje", com.cibertec.util.Constantes.MENSAJE_REG_EXITOSO);
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", com.cibertec.util.Constantes.MENSAJE_REG_ERROR);
			}
			return ResponseEntity.ok(salida);
		}
		
		@PutMapping("/actualizaTesis")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> actualizaTesis(@RequestBody Tesis obj) {
			Map<String, Object> salida = new HashMap<>();
			try {
				Tesis objSalida =  tesisService.insertaActualizaTesis(obj);
				if (objSalida == null) {
					salida.put("mensaje", com.cibertec.util.Constantes.MENSAJE_ACT_ERROR);
				} else {
					salida.put("mensaje", com.cibertec.util.Constantes.MENSAJE_ACT_EXITOSO);
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", com.cibertec.util.Constantes.MENSAJE_ACT_ERROR);
			}
			return ResponseEntity.ok(salida);
		}
		
		@DeleteMapping("/eliminarTesis/{id}")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> eliminarTesis(@PathVariable("id") int idTesis) {
			Map<String, Object> salida = new HashMap<>();
			try {
				tesisService.eliminarTesis(idTesis);
				salida.put("mensaje", com.cibertec.util.Constantes.MENSAJE_ELI_EXITOSO);
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", com.cibertec.util.Constantes.MENSAJE_ELI_ERROR);
			}
			return ResponseEntity.ok(salida);
		}
		
	}
	
		


