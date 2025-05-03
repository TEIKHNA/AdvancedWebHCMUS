package com.example.demo.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Task;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TaskDto;
import com.example.demo.repository.TaskRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    @Override
    public ResponseDto<List<TaskDto>> getTaskList() {
        List<Task> taskList = taskRepository.findAll(); // Fetch all tasks
        List<TaskDto> taskDtoList = taskList.stream()
                .sorted(Comparator.comparingInt(Task::getId)) // Sort tasks by id
                .map(TaskDto::new) // Map to TaskDto with only id, title, and completed
                .collect(Collectors.toList());
        return new ResponseDto<>(taskDtoList, "Success get list of tasks!", true);
    }

    @Transactional
    @Override
    public ResponseDto<TaskDto> addTask(TaskDto taskDto) {
        Task task = Task.builder()
                .title(taskDto.getTitle())
                .completed(taskDto.isCompleted())
                .build();
        var savedTask = taskRepository.save(task);
        TaskDto savedTaskDto = new TaskDto(savedTask);
        return new ResponseDto<>(savedTaskDto, "Success add new Task!", true);
    }

    @Transactional
    @Override
    public ResponseDto<TaskDto> updateTask(int id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDto.getTitle());
        task.setCompleted(taskDto.isCompleted());
        Task updatedTask = taskRepository.save(task);
        TaskDto updatedTaskDto = new TaskDto(updatedTask);
        return new ResponseDto<>(updatedTaskDto, "Task updated successfully", true);
    }
}
