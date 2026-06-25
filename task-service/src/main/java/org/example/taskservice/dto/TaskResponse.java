package org.example.taskservice.dto;

import java.time.LocalDateTime;

public record TaskResponse(

        Long id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}