package com.jesus.apilol.models.services;

import java.util.List;

import com.jesus.apilol.models.entities.Campeones;


public interface ICampeonesService {
	
	public List<Campeones> findAll();
	
	public Campeones findById(Integer id);

    public Campeones save(Campeones campeon);

    public void deleteById(Integer id);

}
