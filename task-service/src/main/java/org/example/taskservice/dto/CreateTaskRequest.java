package org.example.taskservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(

        @NotBlank
        @Size(min = 1, max = 150)
        String name,

        String description
) {

}
