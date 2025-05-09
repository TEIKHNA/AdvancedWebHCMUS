package com.example.demo.utils;

import com.example.demo.task.dto.TaskDto;

import java.util.Comparator;

public class TaskComparator implements Comparator<TaskDto> {
    @Override
    public int compare(TaskDto o1, TaskDto o2) {
        return o1.getOrdinalNumber().compareTo(o2.getOrdinalNumber());
    }
}
