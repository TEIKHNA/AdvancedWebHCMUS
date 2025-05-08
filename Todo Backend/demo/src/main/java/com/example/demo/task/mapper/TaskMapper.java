package com.example.demo.task.mapper;

import com.example.demo.task.domain.Task;
import com.example.demo.task.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto taskToTaskDto(Task task);

    Task taskDtoToTask(TaskDto taskDto);
}
