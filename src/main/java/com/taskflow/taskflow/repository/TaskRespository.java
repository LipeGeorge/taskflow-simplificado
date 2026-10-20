package com.taskflow.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskflow.taskflow.entity.Task;

public interface TaskRespository extends JpaRepository<Task, Long>{
    
}
