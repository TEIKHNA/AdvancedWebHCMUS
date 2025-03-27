package com.hcmus.sakila.repository;

import com.hcmus.sakila.domain.Account;
import com.hcmus.sakila.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
}