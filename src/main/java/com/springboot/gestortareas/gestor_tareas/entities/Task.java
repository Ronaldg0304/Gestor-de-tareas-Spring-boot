package com.springboot.gestortareas.gestor_tareas.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/*@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter*/
@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now(); // Asigna la fecha de creación antes de insertar en la base de datos
        updatedAt = LocalDateTime.now();
    }
}


