package org.example.taskservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.taskservice.dto.CreateTaskRequest;
import org.example.taskservice.dto.TaskResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.taskservice.dto.TaskResponse;
import org.example.taskservice.dto.UpdateRequest;
import org.example.taskservice.entity.Task;
import org.example.taskservice.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskService taskService;

    @Test
    void getTask_shouldReturnTask() throws Exception{

        TaskResponse response = new TaskResponse(
                1L,
                "Test",
                "Description",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(taskService.getById(1L)).thenReturn(response);

        mockMvc.perform(get("/tasks/task/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.description").value("Description"));

    }

    @Test
    void create_shouldReturnCreatedTask() throws Exception{

        CreateTaskRequest taskRequest = new CreateTaskRequest(
                "Test",
                "Description"
        );

        TaskResponse response = new TaskResponse(
                1L,
                "Test",
                "Description",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(taskService.create(taskRequest)).thenReturn(response);

        mockMvc.perform(post("/tasks/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.description").value("Description"));
    }


    @Test
    void should_updateTask() throws Exception{

        UpdateRequest updateRequest = new UpdateRequest(
                "Maks",
                "Some description"
        );

        TaskResponse taskResponse = new TaskResponse(
                1L,
                "Maks",
                "Some description",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(taskService.update(1L, updateRequest))
                .thenReturn(taskResponse);

        mockMvc.perform(patch("/tasks/task/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Maks"))
                .andExpect(jsonPath("$.description").value("Some description"));
    }
}
