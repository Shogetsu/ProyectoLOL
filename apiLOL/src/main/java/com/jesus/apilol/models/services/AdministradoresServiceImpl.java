package com.jesus.apilol.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jesus.apilol.models.dao.IAdministradoresDAO;
import com.jesus.apilol.models.entities.Administradores;

@Service
public class AdministradoresServiceImpl implements IAdministradoresService {
	@Autowired
    private IAdministradoresDAO administradoresDAO;

    @Override
    @Transactional(readOnly=true)
    public List<Administradores> findAll() {
        return (List<Administradores>) administradoresDAO.findAll();
    }
    
    @Override
	@Transactional(readOnly=true)
	public Administradores findById(Integer id) {
		return administradoresDAO.findById(id).orElse(null);
	}

    @Override
    @Transactional
    public Administradores save(Administradores administrador) {
        return administradoresDAO.save(administrador);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
    	administradoresDAO.deleteById(id);
    }

}
