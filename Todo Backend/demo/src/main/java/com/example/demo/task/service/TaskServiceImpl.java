package com.example.demo.task.service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.demo.task.mapper.TaskMapper;
import com.example.demo.user.domain.User;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.task.domain.Task;
import com.example.demo.task.dto.TaskResponseDto;
import com.example.demo.task.dto.TaskDto;
import com.example.demo.task.repository.TaskRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserService userService;

    @Override
    public TaskResponseDto<TaskDto> addTask(TaskDto taskDto) {

        Task task = TaskMapper.INSTANCE.taskDtoToTask(taskDto);

        taskRepository.save(task);

        return new TaskResponseDto<>(taskDto, "New task added successfully!");
    }

    @Override
    public TaskResponseDto<TaskDto> updateTask(UUID taskId, TaskDto taskDto) {
        Task task = taskRepository.findByTaskId(taskId);


        task.setTitle(taskDto.getTitle());
        task.setCompleted(taskDto.isCompleted());
        Task updatedTask = taskRepository.save(task);
        TaskDto updatedTaskDto = new TaskDto(updatedTask);
        return new TaskResponseDto<>(updatedTaskDto, "Task updated successfully", true);
    }
}
