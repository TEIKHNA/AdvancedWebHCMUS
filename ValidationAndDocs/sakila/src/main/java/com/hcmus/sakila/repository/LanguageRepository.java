package com.hcmus.sakila.repository;

import com.hcmus.sakila.domain.Language;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {

    @NotNull
    Optional<Language> findById(@NotNull Integer id);
}