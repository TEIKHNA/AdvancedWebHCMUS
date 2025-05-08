package com.example.demo.task.repository;

import com.example.demo.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface TaskRepository extends JpaRepository<Task, UUID> {

    Task findByTaskId(UUID taskId);

}