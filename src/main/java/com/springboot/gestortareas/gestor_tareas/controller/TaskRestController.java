package com.springboot.gestortareas.gestor_tareas.controller;

import com.springboot.gestortareas.gestor_tareas.entities.Task;
import com.springboot.gestortareas.gestor_tareas.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

    private final TaskService service;

    public TaskRestController(TaskService service) {
        this.service = service;
    }

    @GetMapping //Método lista tareas
    public ResponseEntity<List<Task>> getAllTask() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> detail(@PathVariable Long id) {
        Optional<Task> optionalTask = service.findById(id);
        if (optionalTask.isPresent()) {
            return ResponseEntity.ok(optionalTask.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@RequestBody Task task, @PathVariable Long id) {
        Optional<Task> optionalTask = service.findById(id);
        if (optionalTask.isPresent()) {
            Task taskDb = optionalTask.orElseThrow();
            taskDb.setDescription(task.getDescription());
            taskDb.setTitle(task.getTitle());
            taskDb.setCompleted(task.isCompleted());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(taskDb));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        System.out.println("Task received: " + task.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        Optional<Task> optionalTask = service.deleteById(id);
        if (optionalTask.isPresent()) {
            Task taskDeleted = optionalTask.orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(taskDeleted);
        }
        return ResponseEntity.notFound().build();
    }

}
