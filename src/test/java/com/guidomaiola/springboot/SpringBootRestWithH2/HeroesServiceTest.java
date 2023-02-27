package com.guidomaiola.springboot.SpringBootRestWithH2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.guidomaiola.springboot.SpringBootRestWithH2.model.Hero;
import com.guidomaiola.springboot.SpringBootRestWithH2.repository.HeroesRepository;
import com.guidomaiola.springboot.SpringBootRestWithH2.service.HeroesService;
import com.guidomaiola.springboot.SpringBootRestWithH2.utils.HeroesUtils;

@RunWith(MockitoJUnitRunner.class)
public class HeroesServiceTest {

	private static final String NAME_LIKE_MAN = "MAN";

	@Mock
	private HeroesService heroesService;

	@InjectMocks
	private HeroesRepository heroesRepository;

	@Test
	public void findAllShouldReturnHeroesList() throws Exception {
		
		ArrayList<Hero> heroesNameLikeMan = new ArrayList<>();

		heroesNameLikeMan.add(Hero.builder().id(1).name(HeroesUtils.WONDERWOMAN).build());
		heroesNameLikeMan.add(Hero.builder().id(2).name(HeroesUtils.SPIDERMAN).build());
		heroesNameLikeMan.add(Hero.builder().id(3).name(HeroesUtils.SUPERMAN).build());

		when(heroesRepository.findByNameContaining(NAME_LIKE_MAN)).thenReturn(heroesNameLikeMan);

		assertEquals(heroesService.findByNameLike(NAME_LIKE_MAN).size(), 3);

	}

    @Test
    public void testFindByIdShouldReturnHeroSuccesfull() {

        given(heroesService.findById(1)).willReturn(Optional.of(new Hero(1,HeroesUtils.SUPERMAN)));

        Hero hero = heroesService.findById(1).get();
        
		assertEquals(hero.getId(),1);
		assertEquals(hero.getName(),HeroesUtils.SUPERMAN);

    }

	
    @Test(expected = NoSuchElementException.class)
    public void testFindByInvalidIdShouldThrowException() {

        given(heroesService.findById(100)).willThrow(NoSuchElementException.class);

        heroesService.findById(100);
    }

	public HeroesService getHeroesService() {
		return heroesService;
	}

	public void setHeroesService(HeroesService heroesService) {
		this.heroesService = heroesService;
	}

	public HeroesRepository getHeroesRepository() {
		return heroesRepository;
	}

	public void setHeroesRepository(HeroesRepository heroesRepository) {
		this.heroesRepository = heroesRepository;
	}

}