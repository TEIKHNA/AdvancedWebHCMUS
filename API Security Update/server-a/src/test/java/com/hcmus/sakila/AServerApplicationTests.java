package com.hcmus.sakila;

import com.hcmus.sakila.domain.Film;
import com.hcmus.sakila.domain.type.SpecialFeature;
import com.hcmus.sakila.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class AServerApplicationTests {

    @Autowired
    private FilmRepository filmRepository;

    @Test
    void contextLoads() {
        Film film = filmRepository.findById(2).orElse(null);
        System.out.println(film.getSpecialFeatures().toString());
        film.setSpecialFeatures(Arrays.asList(SpecialFeature.BEHIND_THE_SCENES, SpecialFeature.COMMENTARIES));
        Film newFilm = filmRepository.save(film);
        System.out.println(newFilm.getSpecialFeatures().toString());
        System.out.println();
    }

}
