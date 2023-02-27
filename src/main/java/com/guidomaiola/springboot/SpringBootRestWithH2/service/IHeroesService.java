package com.guidomaiola.springboot.SpringBootRestWithH2.service;

import java.util.List;
import java.util.Optional;

import com.guidomaiola.springboot.SpringBootRestWithH2.model.Hero;

public interface IHeroesService {

	public List<Hero> findAll();

	public Optional<Hero> findById(Integer id);

	public Optional<Hero> save(Hero hero);

	public void delete(Integer id);
}
