package com.iamvickyav.springboot.SpringBootRestWithH2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iamvickyav.springboot.SpringBootRestWithH2.model.Hero;
import com.iamvickyav.springboot.SpringBootRestWithH2.service.HeroesService;

@RestController
public class HeroesController {
	
	@Autowired
	private HeroesService heroesServicel;

	@RequestMapping(value = "/hero/{id}", method = RequestMethod.GET)
    Hero getHero(@PathVariable Integer id){
        return  heroesServicel.findById(id);
    }

    @RequestMapping(value = "/hero", method = RequestMethod.POST)
    Hero addHero(@RequestBody Hero hero){
    	return heroesServicel.save(hero);
    }

    @RequestMapping(value = "/hero", method = RequestMethod.PUT)
    Hero updateHero(@RequestBody Hero hero){
        return heroesServicel.save(hero);
    }

    @RequestMapping(value = "/hero", method = RequestMethod.DELETE)
    void deleteHero(@RequestParam Integer id){
        heroesServicel.delete(id);
    }


    @RequestMapping(value = "/hero", method = RequestMethod.GET)
    List<Hero> getAll(){
        return heroesServicel.findAll();
    }
}
