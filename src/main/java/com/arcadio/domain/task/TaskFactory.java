package com.arcadio.domain.task;

import com.arcadio.domain.attachment.model.Attachment;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.customer.model.Customer;
import com.arcadio.domain.exceptions.IncorrectTypeException;
import com.arcadio.domain.task.dto.TaskDto;
import com.arcadio.domain.task.model.Task;
import com.arcadio.domain.task.repository.TaskRepository;
import com.arcadio.domain.user.userDetails.model.User;
import com.arcadio.domain.user.userTask.model.UserTask;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.Set;

@Component
public class TaskFactory {
    private final TaskRepository taskRepository;

    public TaskFactory(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(TaskDto taskDto) {
        Task task = new Task();
        updateTaskWithDto(task, taskDto);
        return task;
    }

    public void updateTaskWithDto(Task task, TaskDto taskDto) {
        task.setTaskId(taskDto.getTaskId());
        task.setDescription(taskDto.getDescription());
        task.setExecutionDate(taskDto.getExecutionDate());
        task.setStatus(taskDto.getStatus());

        if (taskDto.getCompanyId() != null) {
            Company company = taskRepository.findCompanyById(taskDto.getCompanyId());
            task.setCompany(company);
        }

        if (taskDto.getCustomerId() != null) {
            Customer customer = taskRepository.findCustomerById(taskDto.getCustomerId());
            task.setCustomer(customer);
        }

        if (taskDto.getResponsibleUserId() != null) {
            User responsibleUser = taskRepository.findUserById(taskDto.getResponsibleUserId());
            task.setResponsibleUser(responsibleUser);
        }

        if (taskDto.getAttachmentIds() != null && !taskDto.getAttachmentIds().isEmpty()) {
            Set<Attachment> attachments = new HashSet<>();
            for (Long attachmentId : taskDto.getAttachmentIds()) {
                Attachment attachment = taskRepository.findAttachmentById(attachmentId);
                attachments.add(attachment);
            }
            task.setAttachments(attachments);
        }

        if (taskDto.getUserTaskIds() != null && !taskDto.getUserTaskIds().isEmpty()) {
            Set<UserTask> userTasks = new HashSet<>();
            for (Long userTaskId : taskDto.getUserTaskIds()) {
                UserTask userTask = taskRepository.findUserTaskById(userTaskId);
                userTasks.add(userTask);
            }
            task.setUserTasks(userTasks);
        }
    }
}

