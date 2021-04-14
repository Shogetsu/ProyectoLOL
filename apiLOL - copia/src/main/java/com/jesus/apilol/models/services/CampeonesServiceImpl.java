package com.jesus.apilol.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jesus.apilol.models.dao.ICampeonesDAO;
import com.jesus.apilol.models.entities.Campeones;

@Service
public class CampeonesServiceImpl implements ICampeonesService {
	
	@Autowired
    private ICampeonesDAO campeonesDAO;

    @Override
    @Transactional(readOnly=true)
    public List<Campeones> findAll() {
        return (List<Campeones>) campeonesDAO.findAll();
    }
    
    @Override
	@Transactional(readOnly=true)
	public Campeones findById(Integer id) {
		return campeonesDAO.findById(id).orElse(null);
	}

    @Override
    @Transactional
    public Campeones save(Campeones campeon) {
        return campeonesDAO.save(campeon);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
    	campeonesDAO.deleteById(id);
    }

}
