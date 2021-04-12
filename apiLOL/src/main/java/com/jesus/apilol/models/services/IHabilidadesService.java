package com.jesus.apilol.models.services;

import java.util.List;

import com.jesus.apilol.models.entities.Habilidades;



public interface IHabilidadesService {
	
	public List<Habilidades> findAll();
	
	public Habilidades findById(Integer id);

    public Habilidades save(Habilidades habilidad);

    public void deleteById(Integer id);

}
