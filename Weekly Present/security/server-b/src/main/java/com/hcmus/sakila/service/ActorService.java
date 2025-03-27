package com.hcmus.sakila.service;

import com.hcmus.sakila.dto.request.ActorCreateDto;
import com.hcmus.sakila.dto.request.ActorUpdateDto;
import com.hcmus.sakila.dto.response.ActorDto;
import com.hcmus.sakila.dto.response.ResponseDto;

import java.util.List;

public interface ActorService {

    ResponseDto<List<ActorDto>> getActorsList();

    ResponseDto<ActorDto> addActor(ActorCreateDto actorCreateDto);

    ResponseDto<ActorDto> getActorDetail(Integer actorId);

    ResponseDto<ActorDto> updateActor(Integer id, ActorUpdateDto actorUpdateDto);

    ResponseDto<?> deleteAnActor(Integer id);
}
