package com.hcmus.sakila.service;

import com.hcmus.sakila.domain.Actor;
import com.hcmus.sakila.dto.response.ActorDto;
import com.hcmus.sakila.dto.request.ActorAddDto;
import com.hcmus.sakila.dto.request.ActorUpdateDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.repository.ActorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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
        return new ResponseDto<>(actorsDtoList, "Success get all list of all actors!");
    }

    @Override
    public ResponseDto<ActorDto> getActorDetail(Integer actorId) {
        Optional<Actor> actorOptional = actorRepository.findById(actorId);
        if (actorOptional.isPresent()) {
            Actor actor = actorOptional.get();
            ActorDto actorDto = new ActorDto(actor);
            return new ResponseDto<ActorDto>(actorDto, "Success get actor detail!");
        } else {
            return new ResponseDto<ActorDto>(null, "Actor not found!");
        }
    }

    @Override
    public ResponseDto<ActorDto> addAnActor(ActorAddDto actorAddDto) {
        Actor actor = new Actor(
                null,
                actorAddDto.getFirstName(),
                actorAddDto.getLastName(),
                Calendar.getInstance().getTime().toInstant());
        Actor actorWithId = actorRepository.save(actor);
        ActorDto newActorDto = new ActorDto(actorWithId);
        return new ResponseDto<ActorDto>(newActorDto, "Success add an actor!");
    }

    @Override
    public ResponseDto<ActorDto> updateAnActor(Integer id, ActorUpdateDto actorUpdateDto) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor == null) {
            return new ResponseDto<>(null, "Not found actor with id: " + id + "!");
        }
        if (actorUpdateDto.getFirstName() != null) actor.setFirstName(actorUpdateDto.getFirstName());
        if (actorUpdateDto.getLastName() != null) actor.setLastName(actorUpdateDto.getLastName());
        actor.setLastUpdate(Calendar.getInstance().getTime().toInstant());
        Actor savedActor = actorRepository.save(actor);
        return new ResponseDto<>(new ActorDto(savedActor), "Update an actor successfully!");
    }

    @Override
    public ResponseDto<?> deleteAnActor(Integer id) {
        actorRepository.deleteById(id);
        return new ResponseDto<>(null, "Delete successfully!");
    }
}
