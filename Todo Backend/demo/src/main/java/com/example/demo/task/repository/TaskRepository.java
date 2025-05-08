package com.example.demo.task.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.task.domain.Task;


public interface TaskRepository extends JpaRepository<Task, UUID> {

    Task findByTaskId(UUID taskId);

}