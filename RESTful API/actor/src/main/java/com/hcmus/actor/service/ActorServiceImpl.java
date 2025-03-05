package com.hcmus.actor.service;

import com.hcmus.actor.domain.Actor;
import com.hcmus.actor.dto.ActorDto;
import com.hcmus.actor.dto.ActorDtoRequest;
import com.hcmus.actor.dto.ResponseDto;
import com.hcmus.actor.repository.ActorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
                .map(actor -> new ActorDto(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getLastUpdate()))
                .toList();
        return new ResponseDto<List<ActorDto>>(actorsDtoList, 200, "Success get all list of all actors");
    }

    @Override
    public ResponseDto deleteAActor(Integer id) {
        actorRepository.deleteById(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Delete successfully ");
        responseDto.setStatusCode(200);
        return responseDto;
    }

    @Override
    public ResponseDto updateAActor(Integer id, ActorDtoRequest actorDtoRequest) {
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new RuntimeException());
        actor.setFirstName(actorDtoRequest.getFirstName());
        actor.setLastName(actorDtoRequest.getLastName());
        actor.setLastUpdate(actorDtoRequest.getLastUpdate());
        ResponseDto responseDto = new ResponseDto();
        actorRepository.save(actor);
        responseDto.setMessage("Update successfully ");
        responseDto.setStatusCode(200);
        return responseDto;
    }

    @Override
    public ResponseDto<ActorDto> getActorDetail(Integer actorId) 
    {
        Optional<Actor> actorOptional = actorRepository.findById(actorId);
        if (actorOptional.isPresent()) {
            Actor actor = actorOptional.get();
            ActorDto actorDto = new ActorDto(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getLastUpdate());
            return new ResponseDto<ActorDto>(actorDto, 200, "Success get actor detail");
        } else {
            return new ResponseDto<ActorDto>(null, 404, "Actor not found");
        }
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
