package org.example.taskservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.taskservice.dto.CreateTaskRequest;
import org.example.taskservice.dto.TaskResponse;
import org.example.taskservice.dto.UpdateRequest;
import org.example.taskservice.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{id}")
    public TaskResponse getTask(@PathVariable Long id){
        return taskService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@Valid @RequestBody CreateTaskRequest request) {
        return taskService.create(request);
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse updateTask(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRequest update
    ){

        return taskService.update(id, update);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        taskService.delete(id);
    }
}
