package com.example.taskmanagement;


import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.service.TaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTests {

    @InjectMocks
    private TaskService taskService;
    @Mock
    private TaskRepository taskRepository;
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTask(){
        Task task = new Task(
                null,
                "Test Task",
                "Description",
                LocalDate.now(),
                false);
        when(taskRepository.save(task)).thenReturn(task);

        Task createdTask = taskService.createTask(task);
        assertNotNull(createdTask);
        assertEquals("Test Task",
                createdTask.getName());
        verify(taskRepository,
                times(1))
                .save(task);
    }
    public void testGetTaskById(){
         Task task = new Task(1L,
                 "Test Task",
                 "Description",
                 LocalDate.now(),
                 false);
         when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

         Optional<Task> retrievedTask = taskService.getById(1L);
         assertNotNull(retrievedTask.isPresent());// null mu?
         assertTrue(retrievedTask.isPresent());// null değil mi?
         assertEquals("Test Task", retrievedTask.get().getName());//CreatedTask'ın adı "Test task" mı?
         verify(taskRepository, times(1)).findById(1L);
    }
}
/*
^^@InjectMocks: Bu açıklama, Mockito'nun taskService nesnesini oluşturup gerekli olan
*   bağımlılıkları (taskRepository gibi) otomatik olarak enjekte etmesini sağlar.
*
^^@Mock: Bu açıklama, Mockito'nun taskRepository için bir sahte (mock) nesne oluşturmasını sağlar.
*   Bu sahte nesne, test sırasında gerçek veritabanı erişimi yerine kullanılacaktır.
*
^^@BeforeEach: Bu metodun her testten önce çalıştırılmasını sağlar.
*
^^MockitoAnnotations.openMocks(this): Bu çağrı, bu sınıftaki Mockito açıklamalarını (@InjectMocks ve @Mock) başlatır.
*   Bu, testlerin çalışması için gerekli olan sahte nesnelerin oluşturulmasını sağlar.
*
^^@Test: metodun test olduğunu söyler
*/