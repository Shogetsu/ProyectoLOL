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

import com.jesus.apilol.models.entities.Habilidades;
import com.jesus.apilol.models.entities.Roles;
import com.jesus.apilol.models.services.IRolesService;
//@CrossOrigin(origins= {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = {"Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"})
@RestController
@RequestMapping("/roles")
@CrossOrigin(origins= {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = {"Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"})
public class RolesController {
	@Autowired
	private IRolesService rolesService;
	
	@GetMapping()
	public List<Roles> index(){
		return rolesService.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> show(@PathVariable Integer id){
		
		Roles rol = null;
		Map<String, Object> respuesta = new HashMap<String, Object>();	
		
		try {
			rol = rolesService.findById(id);
		} catch (Exception e) {
			respuesta.put("mensaje", "Error al realizar la consulta sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getStackTrace().toString()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(rol==null) {
			respuesta.put("mensaje", "El Identificador buscado no existe");
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Roles>(rol,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> create(@Valid @RequestBody Roles rol, BindingResult result) {
		
		Roles rolNuevo = null;
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(error->"El campo " + error.getField() + " : " + error.getDefaultMessage())
					.collect(Collectors.toList());
			respuesta.put("errores",errores);
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.BAD_REQUEST);			
		}
		
		
		try {
			rolNuevo = rolesService.save(rol);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al intentar insertar sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		respuesta.put("mensaje", "Rol creado correctamente");
		respuesta.put("rol", rolNuevo);
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Roles rol, BindingResult result, @PathVariable Integer id) {
		Roles rolActual = rolesService.findById(id);
		Roles rolActualizado = null;
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		if(result.hasErrors()) {	// Si hay errores de validación de los campos
			List<String> errores = result.getFieldErrors().stream()
					.map(error->"El campo " + error.getField() + " : " + error.getDefaultMessage())
					.collect(Collectors.toList());
			respuesta.put("errores",errores);
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.BAD_REQUEST);			
		}
		
		if(rolActual==null) { // Intentas actualizar un id que no existe
			respuesta.put("mensaje", "El Identificador buscado no existe");
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.NOT_FOUND);
		}
				
		try {
			rolActual.setNombreRol(rol.getNombreRol());
			rolActual.setCampeoneses(rol.getCampeoneses());
			rolActualizado = rolesService.save(rolActual);
		} catch (DataAccessException e) {	// Saltan errores si incumples algo en la base de datos
			respuesta.put("mensaje", "Error al intentar actualiar sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// Se ha creado todo correcto
		respuesta.put("mensaje", "Rol actualizado correctamente");
		respuesta.put("rol", rolActualizado);
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> borrar(@PathVariable Integer id){
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		try {
			rolesService.deleteById(id);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al intentar borrar sobre la base de datos");
			respuesta.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		respuesta.put("mensaje", "El rol con id " + id + " ha sido borrado con éxito");
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);
	}

}
