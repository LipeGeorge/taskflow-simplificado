package com.taskflow.taskflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taskflow.taskflow.entity.Status;
import com.taskflow.taskflow.entity.Task;
import com.taskflow.taskflow.entity.dto.TaskCreateDTO;
import com.taskflow.taskflow.entity.dto.TaskUpdateDTO;
import com.taskflow.taskflow.repository.TaskRespository;

@Service 
public class TaskService {
    
    private final TaskRespository taskRespository;

    public TaskService(TaskRespository taskRespository){
        this.taskRespository = taskRespository;
    }

    public List<Task> getTasks(){
        return taskRespository.findAll();
    }

    public Task getTaskById(String id){

        var id_long = Long.decode(id);
        var taskDB = taskRespository.findById(id_long);

        if(taskDB.isPresent()){
            Task task = taskDB.get();
            return task;
        }

        // no content with id passed
        return null;

    }

    public List<Task> createTask(TaskCreateDTO taskCreateDTO){
        Task task = new Task();

        task.setTitle(taskCreateDTO.title());
        task.setDescription(taskCreateDTO.description());

        task.setStatus(Status.PENDING);

        taskRespository.save(task);
        return getTasks();
    }

    public List<Task> updateTask(String id, TaskUpdateDTO taskUpdateDTO){

        var task = getTaskById(id);

        if (task != null) {
            
            task.setTitle(taskUpdateDTO.title());
            task.setDescription(taskUpdateDTO.description());
            task.setStatus(taskUpdateDTO.status());

            taskRespository.save(task);

            return getTasks();

        }

        // no content with id passed
        return null;

    }

    public List<Task> deleteTask(String id){

        var task = getTaskById(id);
        if (task != null) {
            taskRespository.deleteById(task.getId());
            return getTasks();
        }

        // no content with id passed
        return null;
    }

}
