package com.hcmus.sakila.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_id_gen")
    @SequenceGenerator(name = "language_id_gen", sequenceName = "language_language_id_seq", allocationSize = 1)
    @Column(name = "language_id", nullable = false)
    private Integer id;

    @Column(name = "name", columnDefinition = "bpchar(20)")
    private String name;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "language")
    private Set<Film> films = new LinkedHashSet<>();
}