package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Task;

import jakarta.transaction.Transactional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    // Fetch all tasks (optional: add pagination if needed)
    @Query("SELECT t FROM Task t")
    List<Task> getTaskList();

    // Add a new task
    @Modifying
    @Transactional
    @Query("INSERT INTO Task (title, completed) VALUES (:title, :completed)")
    void addTask(@Param("title") String title, @Param("completed") boolean completed);

    // Update an existing task
    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.title = :title, t.completed = :completed WHERE t.id = :id")
    void updateTask(@Param("id") int id, @Param("title") String title, @Param("completed") boolean completed);

}