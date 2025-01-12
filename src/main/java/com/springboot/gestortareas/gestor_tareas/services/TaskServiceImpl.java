package com.springboot.gestortareas.gestor_tareas.services;

import com.springboot.gestortareas.gestor_tareas.entities.Task;
import com.springboot.gestortareas.gestor_tareas.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return (List<Task>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Task save(Task task) {
        return repository.save(task);
    }

    @Transactional
    @Override
    public Optional<Task> deleteById(Long id) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()){
            repository.deleteById(id);
            return optionalTask;
        }
        return Optional.empty();
    }
}
