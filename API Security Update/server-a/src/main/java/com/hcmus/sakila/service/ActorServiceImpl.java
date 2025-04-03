package com.hcmus.sakila.service;

import com.hcmus.sakila.model.Actor;
import com.hcmus.sakila.dto.request.ActorCreateDto;
import com.hcmus.sakila.dto.request.ActorUpdateDto;
import com.hcmus.sakila.dto.response.ActorDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.dto.response.Status;
import com.hcmus.sakila.repository.ActorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Override
    public ResponseDto<List<ActorDto>> getActorsList() {
        List<Actor> actorsList = actorRepository.findAll();
        List<ActorDto> actorsDtoList = actorsList.stream()
                .map(ActorDto::new)
                .toList();
        return new ResponseDto<>(Status.SUCCESS, actorsDtoList, null);
    }

    @Override
    public ResponseDto<ActorDto> getActorDetail(Integer actorId) {
        Optional<Actor> actorOptional = actorRepository.findById(actorId);
        if (actorOptional.isPresent()) {
            Actor actor = actorOptional.get();
            ActorDto actorDto = new ActorDto(actor);
            return new ResponseDto<ActorDto>(Status.SUCCESS, actorDto, null);
        }
        return new ResponseDto<ActorDto>(Status.FAIL, null, List.of("Not found actor with id: " + actorId + "!"));
    }

    @Override
    public ResponseDto<ActorDto> addActor(ActorCreateDto actorCreateDto) {
        Actor actor = new Actor(
                null,
                actorCreateDto.getFirstName(),
                actorCreateDto.getLastName(),
                Instant.now());
        Actor createdActor = actorRepository.save(actor);

        ActorDto createdActorDto = new ActorDto(createdActor);
        return new ResponseDto<ActorDto>(Status.SUCCESS, createdActorDto, null);
    }

    @Override
    public ResponseDto<ActorDto> updateActor(Integer id, ActorUpdateDto actorUpdateDto) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor == null) {
            return new ResponseDto<>(Status.FAIL, null, List.of("Not found actor with id: " + id + "!"));
        }
        actor.setFirstName(actorUpdateDto.getFirstName() != null ? actorUpdateDto.getFirstName() : actor.getFirstName());
        actor.setLastName(actorUpdateDto.getLastName() != null ? actorUpdateDto.getLastName() : actor.getLastName());
        actor.setLastUpdate(Instant.now());
        Actor updatedActor = actorRepository.save(actor);

        ActorDto updatedActorDto = new ActorDto(updatedActor);
        return new ResponseDto<ActorDto>(Status.SUCCESS, updatedActorDto, null);
    }

    @Override
    public ResponseDto<?> deleteAnActor(Integer id) {
        try {
            actorRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseDto<>(Status.FAIL, null,
                    List.of("Cannot delete actor with id: " + id + "(" + e.getMessage() + ")"));
        }
        return new ResponseDto<>(Status.SUCCESS, null, null);
    }
}
