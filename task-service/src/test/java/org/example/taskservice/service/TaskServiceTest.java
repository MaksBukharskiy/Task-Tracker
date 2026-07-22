package org.example.taskservice.service;

import org.example.taskservice.dto.CreateTaskRequest;
import org.example.taskservice.dto.TaskResponse;
import org.example.taskservice.dto.UpdateRequest;
import org.example.taskservice.entity.Task;
import org.example.taskservice.exception.TaskNotFoundException;
import org.example.taskservice.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.spec.PSource;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImplementation taskService;


    @Test
    void getById_shouldReturnTask() {
        Task task = Task.builder()
                .id(1L)
                .name("Test")
                .description("Description")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        TaskResponse response = taskService.getById(1L);

        assertEquals(1L, response.id());
        assertEquals("Test", response.name());
        assertEquals("Description", response.description());

        verify(taskRepository).findById(1L);
    }

    @Test
    void getById_shouldThrowException() {
        when(taskRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.getById(1L)
        );

        verify(taskRepository).findById(1L);
    }

    @Test
    void create_shouldSave(){
        CreateTaskRequest request = new CreateTaskRequest(
                "Test",
                "Description"
        );

        TaskResponse response = taskService.create(request);
        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);

        verify(taskRepository).save(captor.capture());
        Task savedTask = captor.getValue();

        assertEquals("Test", savedTask.getName());
        assertEquals("Description", savedTask.getDescription());

        assertNotNull(savedTask.getCreatedAt());
        assertNotNull(savedTask.getUpdatedAt());

    }

    @Test
    void update_shouldUpdateTask(){
        Task task = Task.builder()
                .id(1L)
                .name("Old")
                .description("Old description")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        UpdateRequest request = new UpdateRequest(
                "New",
                "New description"
        );

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        TaskResponse response = taskService.update(1L, request);
        verify(taskRepository).findById(1L);

        assertEquals("New", task.getName());
        assertEquals("New description", task.getDescription());

        assertEquals("New", response.name());
        assertEquals("New description", response.description());

        assertNotNull(task.getUpdatedAt());
    }

    @Test
    void delete_shouldDelete(){

        Task task = Task.builder()
                .id(1L)
                .name("Test")
                .description("Description")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        taskService.delete(1L);

        verify(taskRepository).findById(1L);
        verify(taskRepository).delete(task);
    }

    @Test
    void update_shouldThrowException(){
        UpdateRequest request = new UpdateRequest(
                "New",
                "New description"
        );

        when(taskRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.update(1L, request)
        );

        verify(taskRepository).findById(1L);
    }
}
