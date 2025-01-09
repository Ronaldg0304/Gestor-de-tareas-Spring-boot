package com.springboot.gestortareas.gestor_tareas.repositories;

import com.springboot.gestortareas.gestor_tareas.entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository <Task, Long> {


}
