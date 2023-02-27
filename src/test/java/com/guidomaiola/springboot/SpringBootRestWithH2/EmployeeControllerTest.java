package com.guidomaiola.springboot.SpringBootRestWithH2;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.guidomaiola.springboot.SpringBootRestWithH2.controller.HeroesController;
import com.guidomaiola.springboot.SpringBootRestWithH2.model.Hero;
import com.guidomaiola.springboot.SpringBootRestWithH2.service.HeroesService;
import com.guidomaiola.springboot.SpringBootRestWithH2.utils.HeroesUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(HeroesController.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {


	@Autowired
    private MockMvc mockMvc;

    HeroesService heroesService;
    
    @BeforeAll
    public void setup() {
    	heroesService = mock(HeroesService.class);
    }

    @Test
    public void testGetAllHeroes() throws Exception {
        List<Hero> heroesList = new ArrayList<>();
        heroesList.add(Hero.builder().id(1).name(HeroesUtils.SUPERMAN).build());
        heroesList.add(Hero.builder().id(2).name(HeroesUtils.BATMAN).build());
        heroesList.add(Hero.builder().id(3).name(HeroesUtils.WONDERWOMAN).build());
        heroesList.add(Hero.builder().id(4).name(HeroesUtils.SPIDERMAN).build());
        heroesList.add(Hero.builder().id(5).name(HeroesUtils.HULK).build());

        when(heroesService.findAll()).thenReturn(heroesList);

        this.mockMvc.perform(get("/hero"))
        	.andDo(print())
        	.andExpect(status().isOk());
    }

    @Test
    public void testFindById() throws Exception {

        given(heroesService.findById(1)).willReturn(Optional.of(new Hero(1,HeroesUtils.SUPERMAN)));

        this.mockMvc.perform(get("/hero/1"))
        	.andDo(print())
        	.andExpect(status().isOk());

    }

    @Test
    public void testFindByIdNotExist() throws Exception {

        given(heroesService.findById(1)).willThrow(NoSuchElementException.class);

        this.mockMvc.perform(get("/hero/100"))
        	.andDo(print())
        	.andExpect(status().is4xxClientError());

    }

}
