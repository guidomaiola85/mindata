package com.guidomaiola.springboot.SpringBootRestWithH2.service;

import java.util.List;

import com.guidomaiola.springboot.SpringBootRestWithH2.model.Hero;

public interface IHeroesService {

	public List<Hero> findAll();

	public Hero findById(Integer id);

	public Hero save(Hero hero);
	
	public Hero upodate(Hero hero);

	public void delete(Integer id);
}
