package org.rodko.taskmanager.service;

import org.rodko.taskmanager.model.dto.TaskDto;
import org.rodko.taskmanager.model.entity.TaskEntity;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskEntity createTask(TaskDto taskDto);
    TaskDto getTask(UUID taskId);
    List<TaskDto> getTasks();
    void updateTask(UUID taskId, TaskDto taskDto);
    void deleteTask(UUID taskId);
}