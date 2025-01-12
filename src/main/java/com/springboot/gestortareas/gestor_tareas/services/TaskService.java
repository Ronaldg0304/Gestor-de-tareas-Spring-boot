package com.springboot.gestortareas.gestor_tareas.services;

import com.springboot.gestortareas.gestor_tareas.entities.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface TaskService {

    List<Task> findAll();
    Optional<Task> findById(Long id);
    Task save(Task task);
    Optional<Task> deleteById(Long id);
}
