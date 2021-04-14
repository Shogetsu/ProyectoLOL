package com.jesus.apilol.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jesus.apilol.models.entities.Habilidades;

public interface IHabilidadesDAO extends CrudRepository<Habilidades, Integer>{
	@Query("from Habilidades where id_campeon= ?1")
	public List<Habilidades> getHabilidadesCampeon(Integer x);
}
