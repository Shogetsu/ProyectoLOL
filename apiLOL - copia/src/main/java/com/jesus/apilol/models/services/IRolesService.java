package com.jesus.apilol.models.services;

import java.util.List;

import com.jesus.apilol.models.entities.Roles;



public interface IRolesService {
	
	public List<Roles> findAll();
	
	public Roles findById(Integer id);

    public Roles save(Roles rol);

    public void deleteById(Integer id);

}
