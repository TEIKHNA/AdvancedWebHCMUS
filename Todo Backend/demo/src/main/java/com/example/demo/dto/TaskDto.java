package com.example.demo.dto;

import com.example.demo.domain.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Add this to provide a default constructor
public class TaskDto {
    private Integer id;
    private String title;
    private boolean completed;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.completed = task.isCompleted();
    }
}