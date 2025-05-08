package com.example.demo.task.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.task.dto.DeleteTaskResponseDto;
import com.example.demo.task.dto.TaskResponseDto;
import com.example.demo.task.dto.TaskDto;

public interface TaskService {

    TaskResponseDto addTask(TaskDto taskDto, UUID userId);

    TaskResponseDto updateTask(UUID taskId, UUID userId, TaskDto taskDto);

    DeleteTaskResponseDto deleteTask(UUID taskId, UUID userId);
}
