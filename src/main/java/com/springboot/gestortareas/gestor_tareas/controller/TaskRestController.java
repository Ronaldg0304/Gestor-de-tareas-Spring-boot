package com.springboot.gestortareas.gestor_tareas.controller;

import com.springboot.gestortareas.gestor_tareas.DTO.ApiResponse;
import com.springboot.gestortareas.gestor_tareas.entities.Task;
import com.springboot.gestortareas.gestor_tareas.services.TaskService;
import jakarta.validation.Valid;
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

    @GetMapping //
    public ResponseEntity<ApiResponse<List<Task>>> getAllTasks() {
        List<Task> tasks = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Tasks retrieved successfully", tasks));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> detail(@PathVariable Long id) {
        Optional<Task> optionalTask = service.findById(id);
        return optionalTask.map(task -> ResponseEntity.ok(new ApiResponse<>("Task found", task))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Task not found", null)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> update(@Valid @RequestBody Task task, @PathVariable Long id) {
        Optional<Task> optionalTask = service.findById(id);
        if (optionalTask.isPresent()) {
            Task taskDb = optionalTask.get();
            taskDb.setTitle(task.getTitle());
            taskDb.setDescription(task.getDescription());
            taskDb.setCompleted(task.isCompleted());
            Task updatedTask = service.save(taskDb);
            return ResponseEntity.ok(new ApiResponse<>("Task updated successfully", updatedTask));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>("Task not found", null));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Task>> create(@Valid @RequestBody Task task) {
        Task savedTask = service.save(task);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Task created successfully", savedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        Optional<Task> optionalTask = service.findById(id);
        if (optionalTask.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>("Task deleted successfully", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>("Task not found", null));
    }

}
