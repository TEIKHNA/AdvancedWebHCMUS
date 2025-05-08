package com.example.demo.task.repository;

import java.util.List;
import java.util.UUID;

import com.example.demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.task.domain.Task;

import jakarta.transaction.Transactional;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    Task findByTaskId(UUID taskId);

}