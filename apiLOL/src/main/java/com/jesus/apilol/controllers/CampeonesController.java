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

import com.jesus.apilol.models.entities.Administradores;
import com.jesus.apilol.models.entities.Campeones;
import com.jesus.apilol.models.services.ICampeonesService;

//@CrossOrigin(origins= {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = {"Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"})
@RestController
@RequestMapping("/campeones")
@CrossOrigin(origins= {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.PUT}, allowedHeaders = {"Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"})
public class CampeonesController {
	@Autowired
	private ICampeonesService campeonesService;
	
	@GetMapping()
	public List<Campeones> index(){
		return campeonesService.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> show(@PathVariable Integer id){
		
		Campeones campeon = null;
		Map<String, Object> respuesta = new HashMap<String, Object>();	
		
		try {
			campeon = campeonesService.findById(id);
		} catch (Exception e) {
			respuesta.put("mensaje", "Error al realizar la consulta sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getStackTrace().toString()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(campeon==null) {
			respuesta.put("mensaje", "El Identificador buscado no existe");
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Campeones>(campeon,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> create(@Valid @RequestBody Campeones campeon, BindingResult result) {
		
		Campeones campeonNuevo = null;
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(error->"El campo " + error.getField() + " : " + error.getDefaultMessage())
					.collect(Collectors.toList());
			respuesta.put("errores",errores);
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.BAD_REQUEST);			
		}
		
		
		try {
			campeonNuevo = campeonesService.save(campeon);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al intentar insertar sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		respuesta.put("mensaje", "Campeón creado correctamente");
		respuesta.put("campeon", campeonNuevo);
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Campeones campeon, BindingResult result, @PathVariable Integer id) {
		Campeones campeonActual = campeonesService.findById(id);
		Campeones campeonActualizado = null;
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		if(result.hasErrors()) {	// Si hay errores de validación de los campos
			List<String> errores = result.getFieldErrors().stream()
					.map(error->"El campo " + error.getField() + " : " + error.getDefaultMessage())
					.collect(Collectors.toList());
			respuesta.put("errores",errores);
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.BAD_REQUEST);			
		}
		
		if(campeonActual==null) { // Intentas actualizar un id que no existe
			respuesta.put("mensaje", "El Identificador buscado no existe");
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.NOT_FOUND);
		}
				
		try {
			campeonActual.setNombre(campeon.getNombre());
			campeonActual.setDescripcion(campeon.getDescripcion());
			campeonActual.setDificultad(campeon.getDificultad());
			campeonActual.setImagen(campeon.getImagen());
			campeonActual.setPosicion(campeon.getPosicion());
			campeonActual.setHabilidadeses(campeon.getHabilidadeses());
			campeonActual.setRoles(campeon.getRoles());
			campeonActualizado = campeonesService.save(campeonActual);
		} catch (DataAccessException e) {	// Saltan errores si incumples algo en la base de datos
			respuesta.put("mensaje", "Error al intentar actualiar sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// Se ha creado todo correcto
		respuesta.put("mensaje", "Campeon actualizado correctamente");
		respuesta.put("campeon", campeonActualizado);
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> borrar(@PathVariable Integer id){
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		try {
			campeonesService.deleteById(id);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al intentar borrar sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		respuesta.put("mensaje", "El campeon con id " + id + " ha sido borrado con éxito");
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);
	}

}
