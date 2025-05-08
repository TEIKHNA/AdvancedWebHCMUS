package com.example.demo.task.mapper;

import com.example.demo.task.domain.Task;
import com.example.demo.task.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto taskToTaskDto(Task task);

    @Mapping(target = "user", ignore = true)
    Task taskDtoToTask(TaskDto taskDto);
}
