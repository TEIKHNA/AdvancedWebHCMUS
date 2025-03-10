package com.hcmus.sakila.repository;

import com.hcmus.sakila.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}