package com.hcmus.sakila.repository;

import com.hcmus.sakila.domain.Film;
import com.hcmus.sakila.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query(value = "SELECT * FROM film WHERE rating = CAST(:rating AS mpaa_rating)", nativeQuery = true)
    List<Film> findByRating(@Param("rating") String rating, Pageable range);

    List<Film> findAllByReleaseYear(Integer releaseYear, Pageable range);

    List<Film> findAllByLanguage(Optional<Language> language, Pageable range);
    
    @Query("SELECT f.rating, COUNT(f) FROM Film f GROUP BY f.rating")
    List<Object[]> countFilmsByRating();

    @Query("SELECT f FROM Film f ORDER BY f.length DESC")
    List<Film> findLongestFilms(Pageable pageable);
}