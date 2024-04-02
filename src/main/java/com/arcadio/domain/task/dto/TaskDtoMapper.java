package com.arcadio.domain.task.dto;

import com.arcadio.domain.attachment.model.Attachment;
import com.arcadio.domain.task.model.Task;
import com.arcadio.domain.user.userTask.model.UserTask;

import java.util.stream.Collectors;

public class TaskDtoMapper {
    public static TaskDto map(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTaskId(task.getTaskId());
        taskDto.setCompanyId(task.getCompany().getId());
        taskDto.setCustomerId(task.getCustomer().getId());
        taskDto.setDescription(task.getDescription());
        taskDto.setAttachmentIds(task.getAttachments().stream().map(Attachment::getId).collect(Collectors.toSet()));
        taskDto.setResponsibleUserId(task.getResponsibleUser().getId());
        taskDto.setExecutionDate(task.getExecutionDate());
        taskDto.setStatus(task.getStatus());
        taskDto.setUserTaskIds(task.getUserTasks().stream().map(UserTask::getId).collect(Collectors.toSet()));
        return taskDto;
    }
}
