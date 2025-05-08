package com.example.demo.task.controller;

import java.util.List;
import java.util.UUID;

import com.example.demo.task.dto.DeleteTaskResponseDto;
import com.example.demo.user.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.task.dto.TaskResponseDto;
import com.example.demo.task.dto.TaskDto;
import com.example.demo.task.service.TaskService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    private final JwtService jwtService;

    @PostMapping("/add")
    public ResponseEntity<TaskResponseDto> addTask(@RequestBody TaskDto taskDto,
                                                            @RequestHeader("Authorization") String authorizationHeader) {
        UUID userId = jwtService.extractUserId(authorizationHeader);

        TaskResponseDto response = taskService.addTask(taskDto, userId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable UUID taskId,
                                                      @RequestBody TaskDto taskDto,
                                                      @RequestHeader("Authorization") String authorizationHeader) {

        UUID userId = jwtService.extractUserId(authorizationHeader);

        TaskResponseDto response = taskService.updateTask(taskId, userId, taskDto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<DeleteTaskResponseDto> deleteTask(@PathVariable UUID taskId,
                                                            @RequestHeader("Authorization") String authorizationHeader) {
        UUID userId = jwtService.extractUserId(authorizationHeader);

        DeleteTaskResponseDto responseDto = taskService.deleteTask(taskId, userId);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}