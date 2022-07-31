package com.iamvickyav.springboot.SpringBootRestWithH2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iamvickyav.springboot.SpringBootRestWithH2.model.Hero;

public interface HeroesRepository extends JpaRepository<Hero, Integer>{


}
