package com.jesus.apilol.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jesus.apilol.models.entities.Campeones;
import com.jesus.apilol.models.entities.Habilidades;
import com.jesus.apilol.models.services.IHabilidadesService;
//@CrossOrigin(origins= {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = {"Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"})
@RestController
@RequestMapping("/habilidades")
@CrossOrigin(origins= {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = {"Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"})
public class HabilidadesController {
	@Autowired
	private IHabilidadesService habilidadesService;
	
	@GetMapping()
	public List<Habilidades> index(){
		return habilidadesService.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> show(@PathVariable Integer id){
		
		Habilidades habilidad = null;
		Map<String, Object> respuesta = new HashMap<String, Object>();	
		
		try {
			habilidad = habilidadesService.findById(id);
		} catch (Exception e) {
			respuesta.put("mensaje", "Error al realizar la consulta sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getStackTrace().toString()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(habilidad==null) {
			respuesta.put("mensaje", "El Identificador buscado no existe");
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Habilidades>(habilidad,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> create(@Valid @RequestBody Habilidades habilidad, BindingResult result) {
		
		Habilidades habilidadNuevo = null;
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(error->"El campo " + error.getField() + " : " + error.getDefaultMessage())
					.collect(Collectors.toList());
			respuesta.put("errores",errores);
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.BAD_REQUEST);			
		}
		
		
		try {
			habilidadNuevo = habilidadesService.save(habilidad);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al intentar insertar sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		respuesta.put("mensaje", "Habilidad creado correctamente");
		respuesta.put("habilidad", habilidadNuevo);
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Habilidades habilidad, BindingResult result, @PathVariable Integer id) {
		Habilidades habilidadActual = habilidadesService.findById(id);
		Habilidades habilidadActualizado = null;
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		if(result.hasErrors()) {	// Si hay errores de validación de los campos
			List<String> errores = result.getFieldErrors().stream()
					.map(error->"El campo " + error.getField() + " : " + error.getDefaultMessage())
					.collect(Collectors.toList());
			respuesta.put("errores",errores);
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.BAD_REQUEST);			
		}
		
		if(habilidadActual==null) { // Intentas actualizar un id que no existe
			respuesta.put("mensaje", "El Identificador buscado no existe");
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.NOT_FOUND);
		}
				
		try {
			habilidadActual.setDescripcionHabilidad(habilidad.getDescripcionHabilidad());
			habilidadActual.setNombreHabilidad(habilidad.getNombreHabilidad());
			habilidadActual.setLetraHabilidad(habilidad.getLetraHabilidad());
			habilidadActual.setCampeones(habilidad.getCampeones());
			habilidadActualizado = habilidadesService.save(habilidadActual);
		} catch (DataAccessException e) {	// Saltan errores si incumples algo en la base de datos
			respuesta.put("mensaje", "Error al intentar actualiar sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// Se ha creado todo correcto
		respuesta.put("mensaje", "Habilidad actualizada correctamente");
		respuesta.put("habilidad", habilidadActualizado);
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> borrar(@PathVariable Integer id){
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		try {
			habilidadesService.deleteById(id);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al intentar borrar sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		respuesta.put("mensaje", "La habilidad con id " + id + " ha sido borrado con éxito");
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);
	}
	
	@GetMapping("/campeon/{x}")
	public List<Habilidades> show1(@PathVariable Integer x){
		return habilidadesService.getHabilidadesCampeon(x);
	}

}
