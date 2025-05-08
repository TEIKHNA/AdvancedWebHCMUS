package com.example.demo.task.domain;

import com.example.demo.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID taskId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @Column(name = "ordinal_number")
    private Integer ordinalNumber;
}