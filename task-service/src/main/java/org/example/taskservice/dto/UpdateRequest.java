package org.example.taskservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateRequest(

        @Size(min = 1, max = 150)
        @NotNull
        String name,

        String description
) {

}
