package com.iamvickyav.springboot.SpringBootRestWithH2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamvickyav.springboot.SpringBootRestWithH2.model.Hero;
import com.iamvickyav.springboot.SpringBootRestWithH2.repository.HeroesRepository;

@Service
public class HeroesService {

	@Autowired
	HeroesRepository heroesRepository;

	public List<Hero> findAll() {
		return heroesRepository.findAll();
	}

	public Hero findById(Integer id) {
		return heroesRepository.findById(id).get();
	}

	public Hero save(Hero hero) {
		return heroesRepository.save(hero);
	}

	public void delete(Integer id) {
		heroesRepository.deleteById(id);
		
	}
	
}
