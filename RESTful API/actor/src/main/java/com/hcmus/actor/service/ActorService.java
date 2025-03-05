package com.hcmus.actor.service;

import com.hcmus.actor.dto.ActorDto;
import com.hcmus.actor.dto.ResponseDto;

import java.util.List;

public interface ActorService {

    ResponseDto<List<ActorDto>> getActorsList();
    ResponseDto<ActorDto> getActorDetail(Integer actorId);
}
