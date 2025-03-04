package com.hcmus.actor.service;

import com.hcmus.actor.domain.Actor;
import com.hcmus.actor.dto.ActorDto;
import com.hcmus.actor.dto.ResponseDto;
import com.hcmus.actor.repository.ActorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Override
    public ResponseDto<List<ActorDto>> getActorsList() {
        List<Actor> actorsList = actorRepository.findAll();
        List<ActorDto> actorsDtoList = actorsList.stream()
                .map(actor -> new ActorDto(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getLastUpdate()))
                .toList();
        return new ResponseDto<List<ActorDto>>(actorsDtoList, 200, "Success get all list of all actors");
    }

    @Override
    public ResponseDto<ActorDto> addActor(Actor actor) {
        Actor actorWithId = actorRepository.save(actor);

        ActorDto actorDto = new ActorDto(actorWithId.getId(),
                actorWithId.getFirstName(),
                actorWithId.getLastName(),
                actorWithId.getLastUpdate());

        return new ResponseDto<>(actorDto, 201, "Success add actor");
    }
}
