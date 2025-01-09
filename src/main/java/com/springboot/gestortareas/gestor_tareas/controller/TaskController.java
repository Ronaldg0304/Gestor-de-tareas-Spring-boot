package com.springboot.gestortareas.gestor_tareas.controller;

import com.springboot.gestortareas.gestor_tareas.entities.Task;
import com.springboot.gestortareas.gestor_tareas.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController ("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping //Método lista tareas
    public ResponseEntity<List<Task>> list() {
        return ResponseEntity.ok(service.findAll());
    }

}
