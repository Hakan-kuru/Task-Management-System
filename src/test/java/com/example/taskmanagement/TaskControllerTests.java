package com.example.taskmanagement;

import com.example.taskmanagement.controller.TaskController;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.time.LocalDate;

import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.awaitility.Awaitility.given;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(TaskController.class)
public class TaskControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void testCreateTask() throws Exception {
        Task task = new Task();
        
        // iki tane hata var bunları yarın inceleyeceğim
        // muhtemelen taskService ya da TaskServiceTests kısmında bir hata var

        given(taskService.createTask(task)).willReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/task"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"New Task\", \"description\": \"Task Description\\\", \"dueDate\": \"2024-07-31\", \"isCompleted\": false}")
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New Task"));
    }
}
