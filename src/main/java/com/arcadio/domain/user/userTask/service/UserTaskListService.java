package com.arcadio.domain.user.userTask.service;

import com.arcadio.domain.task.dto.TaskDto;
import com.arcadio.domain.task.dto.TaskDtoMapper;
import com.arcadio.domain.task.repository.TaskRepository;
import com.arcadio.domain.taskStatus.model.TaskStatus;
import com.arcadio.domain.user.userTask.model.UserTask;
import com.arcadio.domain.user.userTask.repository.UserTaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserTaskListService {
    private final TaskRepository taskRepository;
    private final UserTaskRepository userTaskRepository;

    public UserTaskListService(TaskRepository taskRepository, UserTaskRepository userTaskRepository) {
        this.taskRepository = taskRepository;
        this.userTaskRepository = userTaskRepository;
    }
    public Map<TaskStatus, List<TaskDto>> getUserTasks(Long userId) {
        List<UserTask> userTasks = userTaskRepository.findByUserId(userId);
        Map<TaskStatus, List<TaskDto>> tasksByStatus = new HashMap<>();

//        for (UserTask userTask : userTasks) {
//            taskRepository.findUserTaskById(userTask.getId()).ifPresent(task -> {
//                TaskDto mappedTask = TaskDtoMapper.map(task);
//                TaskStatus status = mappedTask.getStatus();
//                tasksByStatus.computeIfAbsent(status, k -> new ArrayList<>()).add(mappedTask);
//            });
//        }

        return tasksByStatus;
    }
}
