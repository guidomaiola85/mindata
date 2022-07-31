package com.iamvickyav.springboot.SpringBootRestWithH2;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.iamvickyav.springboot.SpringBootRestWithH2.model.Employee;
import com.iamvickyav.springboot.SpringBootRestWithH2.model.Hero;
import com.iamvickyav.springboot.SpringBootRestWithH2.service.EmployeeService;
import com.iamvickyav.springboot.SpringBootRestWithH2.service.HeroesService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class EmployeeControllerTest {

    private static final String WONDERWOMAN = "Wonderwoman";

	private static final String HULK = "Hulk";

	private static final String SPIDERMAN = "Spiderman";

	private static final String BATMAN = "Batman";

	private static final String SUPERMAN = "Superman";

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    HeroesService heroesService;

	List<Hero> heroes = new ArrayList<> ();

    @BeforeAll
    public void beforeTests() {
    	heroes.add(new Hero(100, SUPERMAN));
    	heroes.add(new Hero(101, BATMAN));
    	heroes.add(new Hero(102, SPIDERMAN));
        heroes.add(new Hero(103, HULK));
        heroes.add(new Hero(104, WONDERWOMAN));
    }

    @Test
    public void testGetAllHeroes() throws Exception {
    	

        List<Hero> heroesList = heroes;

        given(heroesService.findAll()).willReturn(heroesList);

        this.mockMvc.perform(get("/hero")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }

}
