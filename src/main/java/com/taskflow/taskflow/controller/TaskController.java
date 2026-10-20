package com.taskflow.taskflow.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskflow.taskflow.entity.Task;
import com.taskflow.taskflow.entity.dto.TaskCreateDTO;
import com.taskflow.taskflow.entity.dto.TaskUpdateDTO;
import com.taskflow.taskflow.service.TaskService;

@RestController 
@RequestMapping("/v1/tasks")
public class TaskController {
    
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(){
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskByid(@PathVariable String id){

        var task = taskService.getTaskById(id);

        if (task != null) {
            return ResponseEntity.ok(task);
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<List<Task>> createTask(@RequestBody TaskCreateDTO taskCreateDTO){
        return ResponseEntity.ok(taskService.createTask(taskCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Task>> updateTask(@PathVariable String id, @RequestBody TaskUpdateDTO taskUpdateDTO){

        var task = taskService.updateTask(id, taskUpdateDTO);
        if (task != null) {
            return ResponseEntity.ok(task);    
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        
        var task = taskService.deleteTask(id);

        if (task != null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
