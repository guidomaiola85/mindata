package com.guidomaiola.springboot.SpringBootRestWithH2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guidomaiola.springboot.SpringBootRestWithH2.model.Hero;

import javax.swing.text.html.Option;

public interface HeroesRepository extends JpaRepository<Hero, Integer>{

	List<Hero> findByNameContaining(String name);

	Hero save(Hero hero);


}
