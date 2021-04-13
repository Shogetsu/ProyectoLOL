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
import com.jesus.apilol.models.services.IAdministradoresService;
//@CrossOrigin(origins= {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = {"Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"})
@RestController
@RequestMapping("/administradores")
@CrossOrigin(origins= {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = {"Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"})
public class AdministradoresController {
	@Autowired
	private IAdministradoresService administradoresService;
	
	@GetMapping()
	public List<Administradores> index(){
		return administradoresService.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> show(@PathVariable Integer id){
		
		Administradores administrador = null;
		Map<String, Object> respuesta = new HashMap<String, Object>();	
		
		try {
			administrador = administradoresService.findById(id);
		} catch (Exception e) {
			respuesta.put("mensaje", "Error al realizar la consulta sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getStackTrace().toString()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(administrador==null) {
			respuesta.put("mensaje", "El Identificador buscado no existe");
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Administradores>(administrador,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> create(@Valid @RequestBody Administradores administrador, BindingResult result) {
		
		Administradores administradorNuevo = null;
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(error->"El campo " + error.getField() + " : " + error.getDefaultMessage())
					.collect(Collectors.toList());
			respuesta.put("errores",errores);
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.BAD_REQUEST);			
		}
		
		
		try {
			administradorNuevo = administradoresService.save(administrador);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al intentar insertar sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		respuesta.put("mensaje", "Administrador creado correctamente");
		respuesta.put("administrador", administradorNuevo);
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Administradores administrador, BindingResult result, @PathVariable Integer id) {
		Administradores administradorActual = administradoresService.findById(id);
		Administradores administradorActualizado = null;
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		if(result.hasErrors()) {	// Si hay errores de validación de los campos
			List<String> errores = result.getFieldErrors().stream()
					.map(error->"El campo " + error.getField() + " : " + error.getDefaultMessage())
					.collect(Collectors.toList());
			respuesta.put("errores",errores);
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.BAD_REQUEST);			
		}
		
		if(administradorActual==null) { // Intentas actualizar un id que no existe
			respuesta.put("mensaje", "El Identificador buscado no existe");
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.NOT_FOUND);
		}
				
		try {
			administradorActual.setCorreo(administrador.getCorreo());
			administradorActual.setUsuario(administrador.getUsuario());
			administradorActual.setPassword(administrador.getPassword());
			//clienteActual.setCreateAt(cliente.getCreateAt());
			administradorActualizado = administradoresService.save(administradorActual);
		} catch (DataAccessException e) {	// Saltan errores si incumples algo en la base de datos
			respuesta.put("mensaje", "Error al intentar actualiar sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// Se ha creado todo correcto
		respuesta.put("mensaje", "Administrador actualizado correctamente");
		respuesta.put("administrador", administradorActualizado);
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> borrar(@PathVariable Integer id){
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		try {
			administradoresService.deleteById(id);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al intentar borrar sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		respuesta.put("mensaje", "El administrador con id " + id + " ha sido borrado con éxito");
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);
	}

}
