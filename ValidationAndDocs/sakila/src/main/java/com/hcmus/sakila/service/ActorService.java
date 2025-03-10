package com.hcmus.sakila.service;

import com.hcmus.sakila.dto.ActorDto;
import com.hcmus.sakila.dto.response.ResponseDto;

import java.util.List;

public interface ActorService {

    ResponseDto<List<ActorDto>> getActorsList();

    ResponseDto<ActorDto> updateAnActor(Integer id, ActorDto actorDto);

    ResponseDto<?> deleteAnActor(Integer id);

    ResponseDto<ActorDto> getActorDetail(Integer actorId);

    ResponseDto<ActorDto> addActor(ActorDto actorDto);
}
