package com.jesus.apilol.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jesus.apilol.models.dao.IHabilidadesDAO;
import com.jesus.apilol.models.entities.Habilidades;

@Service
public class HabilidadesServiceImpl  implements IHabilidadesService{
	
	@Autowired
    private IHabilidadesDAO habilidadesDAO;

    @Override
    @Transactional(readOnly=true)
    public List<Habilidades> findAll() {
        return (List<Habilidades>) habilidadesDAO.findAll();
    }
    
    @Override
	@Transactional(readOnly=true)
	public Habilidades findById(Integer id) {
		return habilidadesDAO.findById(id).orElse(null);
	}

    @Override
    @Transactional
    public Habilidades save(Habilidades habilidad) {
        return habilidadesDAO.save(habilidad);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
    	habilidadesDAO.deleteById(id);
    }
}
