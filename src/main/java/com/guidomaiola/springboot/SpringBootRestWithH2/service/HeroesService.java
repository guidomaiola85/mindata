package com.guidomaiola.springboot.SpringBootRestWithH2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.guidomaiola.springboot.SpringBootRestWithH2.model.Hero;
import com.guidomaiola.springboot.SpringBootRestWithH2.repository.HeroesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CacheConfig(cacheNames = { "heroes" })
@Service
public class HeroesService implements IHeroesService {

	@Autowired
	HeroesRepository heroesRepository;

	@Cacheable("heroes")
	public List<Hero> findAll() {
		log.info("HeroesService: FindAll method");
		return this.heroesRepository.findAll();
	}

	@Cacheable("heroes")
	public Hero findById(Integer id) {
		log.info("HeroesService: FindById with id {}", id);
		return this.heroesRepository.findById(id).get();
	}

	@Cacheable("heroes")
	public Hero save(Hero hero) {
		Hero savedHero = this.heroesRepository.save(hero);
		log.info("HeroesService: FindById with id {}, name {}", savedHero.getId(), savedHero.getName());
		return savedHero;
	}
	
	@CachePut(key = "#hero.id")
	public Hero upodate(Hero hero) {
		Hero savedHero = this.heroesRepository.save(hero);
		log.info("HeroesService: FindById with id {}, name {}", savedHero.getId(), savedHero.getName());
		return savedHero;
	}

	@CacheEvict(key = "#hero.id")
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
