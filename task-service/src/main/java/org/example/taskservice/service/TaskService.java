package org.example.taskservice.service;

import org.example.taskservice.dto.TaskResponse;

public interface TaskService {

    TaskResponse getById(Long id);
    TaskResponse create(TaskCreateRequest request);
    TaskResponse update(Long id, TaskUpdateRequest request);

    void delete(Long id);
}