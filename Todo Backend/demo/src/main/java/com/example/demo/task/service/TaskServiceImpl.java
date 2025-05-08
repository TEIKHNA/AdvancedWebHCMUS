package com.example.demo.task.service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.demo.task.dto.DeleteTaskResponseDto;
import com.example.demo.task.mapper.TaskMapper;
import com.example.demo.user.domain.User;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.task.domain.Task;
import com.example.demo.task.dto.TaskResponseDto;
import com.example.demo.task.dto.TaskDto;
import com.example.demo.task.repository.TaskRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserService userService;

    @Override
    public TaskResponseDto addTask(TaskDto taskDto, UUID userId) {

        Task task = TaskMapper.INSTANCE.taskDtoToTask(taskDto);

        User user = userService.findByUserId(userId);

        task.setUser(user);

        taskRepository.save(task);

        return new TaskResponseDto(taskDto, "New task added successfully!");
    }

    @Override
    public TaskResponseDto updateTask(UUID taskId, UUID userId, TaskDto taskDto) {
        Task task = taskRepository.findByTaskId(taskId);

        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task " + taskId + " has not been found!");
        }

        if (task.getUser().getUserId() != userId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not allowed to update this task!");
        }

        task.setTitle(taskDto.getTitle());

        task.setIsCompleted(taskDto.getIsCompleted());

        task.setOrdinalNumber(taskDto.getOrdinalNumber());

        Task updatedTask = taskRepository.save(task);

        TaskDto updatedTaskDto = TaskMapper.INSTANCE.taskToTaskDto(updatedTask);

        return new TaskResponseDto(updatedTaskDto, "Task has been updated successfully");
    }

    @Override
    public DeleteTaskResponseDto deleteTask(UUID taskId, UUID userId) {

        Task task = taskRepository.findByTaskId(taskId);

        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task " + taskId + " has not been found!");
        }

        if (task.getUser().getUserId() != userId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not allowed to delete this task!");
        }

        taskRepository.delete(task);

        return new DeleteTaskResponseDto(taskId, "Task has been deleted successfully!");
    }
}
