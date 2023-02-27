package com.guidomaiola.springboot.SpringBootRestWithH2.service;

import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.guidomaiola.springboot.SpringBootRestWithH2.model.Hero;
import com.guidomaiola.springboot.SpringBootRestWithH2.repository.HeroesRepository;

import lombok.extern.slf4j.Slf4j;

import javax.swing.text.html.Option;

@Slf4j
@CacheConfig(cacheNames = { "heroes" })
@Setter
@Getter
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
	public Optional<Hero> findById(Integer id) {
		log.info("HeroesService: FindById with id {}", id);
		return this.heroesRepository.findById(id);
	}

	@Cacheable("heroes")
	public Optional<Hero> save(Hero hero) {
		Optional<Hero> savedHero = Optional.of(this.heroesRepository.save(hero));
		log.info("HeroesService: FindById with id {}, name {}", savedHero.get().getId(), savedHero.get().getName());
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

}
