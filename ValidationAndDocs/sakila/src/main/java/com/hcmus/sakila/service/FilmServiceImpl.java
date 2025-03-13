package com.hcmus.sakila.service;

import com.hcmus.sakila.repository.FilmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public interface FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;


}
