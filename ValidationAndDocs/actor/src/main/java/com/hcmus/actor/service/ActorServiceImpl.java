package com.hcmus.actor.service;

import com.hcmus.actor.domain.Actor;
import com.hcmus.actor.dto.ActorDto;
import com.hcmus.actor.dto.ResponseDto;
import com.hcmus.actor.repository.ActorRepository;
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
    public ResponseDto<?> deleteAnActor(Integer id) {
        actorRepository.deleteById(id);
        return new ResponseDto<>(null, "Delete successfully!");
    }

    @Override
    public ResponseDto<ActorDto> updateAnActor(Integer id, ActorDto actorDto) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor == null) {
            return new ResponseDto<>(null, "Not found actor with id: " + id + "!");
        }
        actor.setFirstName(actorDto.getFirstName());
        actor.setLastName(actorDto.getLastName());
        actor.setLastUpdate(Calendar.getInstance().getTime().toInstant());

        Actor savedActor = actorRepository.save(actor);
        return new ResponseDto<>(new ActorDto(savedActor), "Update successfully!");
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
    public ResponseDto<ActorDto> addActor(ActorDto actorDto) {
        Actor actor = new Actor(
                null,
                actorDto.getFirstName(),
                actorDto.getLastName(),
                Calendar.getInstance().getTime().toInstant());
        Actor actorWithId = actorRepository.save(actor);

        ActorDto newActorDto = new ActorDto(actorWithId);

        return new ResponseDto<ActorDto>(newActorDto, "Success add actor!");
    }
}
