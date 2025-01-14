package com.springboot.gestortareas.gestor_tareas.controller;

import com.springboot.gestortareas.gestor_tareas.dto.ApiResponse;
import com.springboot.gestortareas.gestor_tareas.entities.Task;
import com.springboot.gestortareas.gestor_tareas.errors.TaskNotFoundException;
import com.springboot.gestortareas.gestor_tareas.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

    private final TaskService service;

    public TaskRestController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Task>>> getAllTasks() {
        List<Task> tasks = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Tasks retrieved successfully", tasks, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> detail(@PathVariable Long id) throws TaskNotFoundException {
        Task task = findTaskById(id);
        return ResponseEntity.ok(new ApiResponse<>("Task retrieved successfully", task, HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> update(@Valid @RequestBody Task task, @PathVariable Long id) throws TaskNotFoundException {
        Task taskDb = findTaskById(id);
        taskDb.setTitle(task.getTitle());
        taskDb.setDescription(task.getDescription());
        taskDb.setCompleted(task.isCompleted());
        Task updatedTask = service.save(taskDb);
        return ResponseEntity.ok(new ApiResponse<>("Task details updated successfully", updatedTask, HttpStatus.CREATED));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Task>> create(@Valid @RequestBody Task task) {
        Task savedTask = service.save(task);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Task created successfully", savedTask, HttpStatus.CREATED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> delete(@PathVariable Long id) throws TaskNotFoundException {
        Task taskDb = findTaskById(id);
        service.deleteById(taskDb.getId());
        return ResponseEntity.ok(new ApiResponse<>("Task deleted successfully", taskDb, HttpStatus.OK));
    }

    private Task findTaskById(Long id) throws TaskNotFoundException {
        Task taskFound = service.findById(id).get();
        return taskFound;
    }
}