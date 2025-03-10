package com.hcmus.sakila.repository;

import com.hcmus.sakila.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}