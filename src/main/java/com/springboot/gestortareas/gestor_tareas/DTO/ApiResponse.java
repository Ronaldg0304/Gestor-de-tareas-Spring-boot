package com.springboot.gestortareas.gestor_tareas.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ApiResponse<T> {

    private String message;
    private T data;

}
