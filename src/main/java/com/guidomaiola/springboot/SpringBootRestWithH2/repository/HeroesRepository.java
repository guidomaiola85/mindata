package com.guidomaiola.springboot.SpringBootRestWithH2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guidomaiola.springboot.SpringBootRestWithH2.model.Hero;

public interface HeroesRepository extends JpaRepository<Hero, Integer>{

	List<Hero> findByNameContaining(String name);

}
