package com.example.demo.task.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.task.dto.TaskResponseDto;
import com.example.demo.task.dto.TaskDto;

public interface TaskService {

    TaskResponseDto<TaskDto> addTask(TaskDto taskDto);

    TaskResponseDto<TaskDto> updateTask(UUID taskId, TaskDto taskDto);

}
