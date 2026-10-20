package com.taskflow.taskflow.entity.dto;

import com.taskflow.taskflow.entity.Status;

public record TaskUpdateDTO(
    String title,
    String description,
    Status status
) {
    
}
