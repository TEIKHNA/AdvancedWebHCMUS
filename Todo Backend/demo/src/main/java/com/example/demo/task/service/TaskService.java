package com.example.demo.task.service;

import com.example.demo.task.dto.DeleteTaskResponseDto;
import com.example.demo.task.dto.TaskDto;
import com.example.demo.task.dto.TaskResponseDto;

import java.util.UUID;

public interface TaskService {

    TaskResponseDto addTask(TaskDto taskDto, UUID userId);

    TaskResponseDto updateTask(UUID taskId, UUID userId, TaskDto taskDto);

    DeleteTaskResponseDto deleteTask(UUID taskId, UUID userId);
}
