package org.example.taskservice.service;

import lombok.RequiredArgsConstructor;
import org.example.taskservice.dto.CreateTaskRequest;
import org.example.taskservice.dto.TaskResponse;
import org.example.taskservice.dto.UpdateRequest;
import org.example.taskservice.entity.Task;
import org.example.taskservice.exception.TaskNotFoundException;
import org.example.taskservice.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository taskRepository;

    public TaskResponse mapToResponse(Task task){
        return new TaskResponse(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse getById(Long id){

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return mapToResponse(task);
    }

    @Override
    public TaskResponse create(CreateTaskRequest createRequest){

        Task task = Task.builder()
                .name(createRequest.name())
                .description(createRequest.description())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        taskRepository.save(task);

        return mapToResponse(task);
    }

    @Override
    public TaskResponse update(Long id, UpdateRequest request){

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setName(request.name());
        task.setDescription(request.description());
        task.setUpdatedAt(LocalDateTime.now());

        return mapToResponse(task);
    }

    @Override
    public void delete(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(task);
    }
}