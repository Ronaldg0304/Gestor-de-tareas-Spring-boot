package com.springboot.gestortareas.gestor_tareas.services;

import com.springboot.gestortareas.gestor_tareas.entities.Task;
import com.springboot.gestortareas.gestor_tareas.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TaskService{

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Task save(Task task) {
        return null;
    }

    @Override
    public Optional<Task> deleteById(Long id) {
        return Optional.empty();
    }
}
