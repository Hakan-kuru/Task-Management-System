package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/taks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id){// @ ??
        Optional<Task> task =taskService.getTaskById(id);//getTaskById???
        if (task.isPresent()){
            return ResponseEntity.ok(task.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task updatedTask )
    {
        Optional<Task> existingTask = taskService.getTaskById(id);
        if (existingTask.isPresent()) {
            updatedTask.setId(id); // Ensure the ID is set correctly
            Task updated = taskService.updateTask(updatedTask);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
