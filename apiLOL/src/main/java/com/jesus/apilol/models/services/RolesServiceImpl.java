package com.jesus.apilol.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jesus.apilol.models.dao.IRolesDAO;
import com.jesus.apilol.models.entities.Roles;

@Service
public class RolesServiceImpl implements IRolesService {
	@Autowired
    private IRolesDAO rolesDAO;

    @Override
    @Transactional(readOnly=true)
    public List<Roles> findAll() {
        return (List<Roles>) rolesDAO.findAll();
    }
    
    @Override
	@Transactional(readOnly=true)
	public Roles findById(Integer id) {
		return rolesDAO.findById(id).orElse(null);
	}

    @Override
    @Transactional
    public Roles save(Roles rol) {
        return rolesDAO.save(rol);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
    	rolesDAO.deleteById(id);
    }

}
