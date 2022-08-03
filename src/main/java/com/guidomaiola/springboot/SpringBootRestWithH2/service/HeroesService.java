package com.guidomaiola.springboot.SpringBootRestWithH2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidomaiola.springboot.SpringBootRestWithH2.model.Hero;
import com.guidomaiola.springboot.SpringBootRestWithH2.repository.HeroesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HeroesService {

	@Autowired
	HeroesRepository heroesRepository;

	public List<Hero> findAll() {
		log.info("HeroesService: FindAll method");
		return this.heroesRepository.findAll();
	}

	public Hero findById(Integer id) {
		log.info("HeroesService: FindById with id {}", id);
		return this.heroesRepository.findById(id).get();
	}

	public Hero save(Hero hero) {
		Hero savedHero = this.heroesRepository.save(hero);
		log.info("HeroesService: FindById with id {}, name {}", savedHero.getId(), savedHero.getName());
		return savedHero;
	}

	public void delete(Integer id) {
		log.info("HeroesService: Delete Hero with id {}", id);
		this.heroesRepository.deleteById(id);
		
	}

	public List<Hero> findByNameLike(String nameLike) {
		return this.heroesRepository.findByNameContaining(nameLike);
	}

	public HeroesRepository getHeroesRepository() {
		return heroesRepository;
	}

	public void setHeroesRepository(HeroesRepository heroesRepository) {
		this.heroesRepository = heroesRepository;
	}

}
