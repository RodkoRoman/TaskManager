package org.rodko.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.rodko.taskmanager.model.dto.TaskDto;
import org.rodko.taskmanager.model.entity.TaskEntity;
import org.rodko.taskmanager.repository.TaskRepository;
import org.rodko.taskmanager.service.TaskService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class RepositoryTaskService implements TaskService {

    private final TaskRepository repository;

    @Transactional
    public TaskEntity createTask(TaskDto taskDto) {
        return repository.save(
                new TaskEntity(
                        UUID.randomUUID(),
                        taskDto.getName(),
                        taskDto.getDescription(),
                        Instant.now(),
                        false
                )
        );
    }

    public TaskDto getTask(UUID taskId) {

        TaskEntity taskEntity = repository.findById(taskId)
                .orElseThrow();

        return new TaskDto(
                taskEntity.getId(),
                taskEntity.getName(),
                taskEntity.getDescription(),
                taskEntity.getCreateAt(),
                taskEntity.getIsDeleted()
        );
    }

    public List<TaskDto> getTasks() {

        List<TaskDto> taskDtoList = new ArrayList<>();
        List<TaskEntity> taskEntityList = repository.findAll();
        for (TaskEntity task :
                taskEntityList) {
            taskDtoList.add(new TaskDto(
                    task.getId(),
                    task.getName(),
                    task.getDescription(),
                    task.getCreateAt(),
                    task.getIsDeleted()));
        }
        return taskDtoList;
    }

    @Transactional
    public void updateTask(UUID taskId, TaskDto taskDto) {
        TaskEntity taskEntity = repository.findById(taskId)
                .orElseThrow();
        if (taskDto.getName() != null) {
            taskEntity.setName(taskDto.getName());
        }
        if (taskDto.getDescription() != null) {
            taskEntity.setDescription(taskDto.getDescription());
        }
        repository.save(taskEntity);
    }

    @Transactional
    public void deleteTask(UUID taskId) {
        TaskEntity taskEntity = repository.findById(taskId)
                .orElseThrow();
        taskEntity.setIsDeleted(true);
        repository.save(taskEntity);
    }
}
