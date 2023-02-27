package com.guidomaiola.springboot.SpringBootRestWithH2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guidomaiola.springboot.SpringBootRestWithH2.model.Hero;
import com.guidomaiola.springboot.SpringBootRestWithH2.service.HeroesService;


@RestController
public class HeroesController {
	
	@Autowired
	private HeroesService heroesServiceImpl;

	@RequestMapping(value = "/hero/{id}", method = RequestMethod.GET)
    ResponseEntity<Hero> getHero(@PathVariable Integer id){
        var hero = heroesServiceImpl.findById(id);
        if (hero.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(hero.get(), HttpStatus.OK);
    }

	@RequestMapping(value = "/hero/name/like/{nameLike}", method = RequestMethod.GET)
    ResponseEntity<List<Hero>> getHeroesByNameLike(@PathVariable String nameLike){
        var heroes = heroesServiceImpl.findByNameLike(nameLike);
        if (heroes.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }

    @RequestMapping(value = "/hero", method = RequestMethod.POST)
    ResponseEntity<Hero> addHero(@RequestBody Hero hero){
        var savedHero = heroesServiceImpl.save(hero);
        return new ResponseEntity<>(savedHero.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/hero", method = RequestMethod.PUT)
    ResponseEntity<Hero> updateHero(@RequestBody Hero hero){
        var updatedHero = heroesServiceImpl.save(hero);
        return new ResponseEntity<>(updatedHero.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/hero", method = RequestMethod.DELETE)
    void deleteHero(@RequestParam Integer id){
        heroesServiceImpl.delete(id);
    }


    @RequestMapping(value = "/hero", method = RequestMethod.GET)
    ResponseEntity<List<Hero>> getAll(){
        List<Hero> heroes = heroesServiceImpl.findAll();
        if (heroes.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }
}
