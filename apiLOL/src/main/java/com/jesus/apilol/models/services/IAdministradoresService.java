package com.jesus.apilol.models.services;

import java.util.List;

import com.jesus.apilol.models.entities.Administradores;

public interface IAdministradoresService {
	
	public List<Administradores> findAll();
	
	public Administradores findById(Integer id);

    public Administradores save(Administradores administrador);

    public void deleteById(Integer id);

}
