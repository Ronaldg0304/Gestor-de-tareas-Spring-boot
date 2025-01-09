package com.springboot.gestortareas.gestor_tareas.controller;

import com.springboot.gestortareas.gestor_tareas.services.TaskService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }


}
