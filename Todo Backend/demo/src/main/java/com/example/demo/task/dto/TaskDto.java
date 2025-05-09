package com.example.demo.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private UUID taskId;
    private String title;
    private Boolean isCompleted;
    private Integer ordinalNumber;
}