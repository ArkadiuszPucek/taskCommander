package com.arcadio.domain.task.dto;

import com.arcadio.domain.taskStatus.model.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class TaskDto {
    private Long id;
    private String taskId;
    private Long companyId;
    private Long customerId;
    private String description;
    private Set<Long> attachmentIds;
    private Long responsibleUserId;
    private LocalDate executionDate;
    private TaskStatus status;
    private Set<Long> userTaskIds;
}
