package com.hcmus.sakila.repository;

import com.hcmus.sakila.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    List<Film> findByTitleContainingIgnoreCase(String keyword);
}