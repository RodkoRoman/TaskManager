package org.rodko.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.rodko.taskmanager.model.dto.TaskDto;
import org.rodko.taskmanager.model.entity.TaskEntity;
import org.rodko.taskmanager.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JdbcTaskService implements TaskService {

    @Override
    public TaskEntity createTask(TaskDto taskDto) {
        return null;
    }

    @Override
    public TaskDto getTask(UUID taskId) {
        return null;
    }

    @Override
    public List<TaskDto> getTasks() {
        return null;
    }

    @Override
    public void updateTask(UUID taskId, TaskDto taskDto) {

    }

    @Override
    public void deleteTask(UUID taskId) {

    }
}
