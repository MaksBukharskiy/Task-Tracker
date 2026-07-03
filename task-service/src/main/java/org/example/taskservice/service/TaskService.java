package org.example.taskservice.service;

import org.example.taskservice.dto.CreateTaskRequest;
import org.example.taskservice.dto.TaskResponse;
import org.example.taskservice.dto.UpdateRequest;

public interface TaskService {

    TaskResponse getById(Long id);
    TaskResponse create(CreateTaskRequest request);
    TaskResponse update(Long id, UpdateRequest request);

    void delete(Long id);
}