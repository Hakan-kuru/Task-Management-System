package com.example.taskmanagement;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Optional;

import static org.awaitility.Awaitility.given;

@WebMvcTest
public class TaskControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void testCreateTask() throws Exception {

        Task existingTask = new Task(1L,
                "Existing Task",
                "Existing Task Description",
                LocalDate.now(),
                false);
        Task updatedTask = new Task(1L,
                "Existing Task",
                "Existing Task Description",
                LocalDate.now(),
                false);
        Mockito.when(taskService.getTaskById(1L)).thenReturn(Optional.of(existingTask));
        Mockito.when(taskService.updateTask(Mockito.any(Task.class))).thenReturn(updatedTask);


        mockMvc.perform(MockMvcRequestBuilders.put("/api/task/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"New Task\", \"description\": \"Task Description\", \"dueDate\": \"2024-07-31\", \"isCompleted\": false}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New Task"));
        /*
        Task task = new Task();
        
        // iki tane hata var bunları yarın inceleyeceğim
        // muhtemelen taskService ya da TaskServiceTests kısmında bir hata var

        given(taskService.createTask(task)).willReturn(task);
    */
    }
}
