package org.rodko.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.rodko.taskmanager.model.dto.TaskDto;
import org.rodko.taskmanager.model.entity.TaskEntity;
import org.rodko.taskmanager.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.*;

@RequiredArgsConstructor
public class EntityManagerTaskService implements TaskService {


    private final EntityManager entityManager;

    @Override
    @Transactional
    public TaskEntity createTask(TaskDto taskDto) {
        TaskEntity taskEntity = new TaskEntity(
                UUID.randomUUID(),
                taskDto.getName(),
                taskDto.getDescription(),
                Instant.now(),
                false);
        entityManager.persist(taskEntity);
        return taskEntity;
    }

    @Override
    public TaskDto getTask(UUID taskId) {

        TaskEntity taskEntity = Optional.ofNullable(
                        entityManager.find(TaskEntity.class, taskId)
                )
                .orElseThrow();

        return new TaskDto(
                taskEntity.getId(),
                taskEntity.getName(),
                taskEntity.getDescription(),
                taskEntity.getCreateAt(),
                taskEntity.getIsDeleted());
    }

    @Override
    public List<TaskDto> getTasks() {

        List<TaskDto> taskDtoList = new ArrayList<>();
        List<TaskEntity> taskEntityList = entityManager.createQuery("from TaskEntity").getResultList();
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

    @Override
    @Transactional
    public void updateTask(UUID taskId, TaskDto taskDto) {
        TaskEntity taskEntity = Optional.ofNullable(
                        entityManager.find(TaskEntity.class, taskId)
                )
                .orElseThrow();

        if (taskDto.getName() != null) {
            taskEntity.setName(taskDto.getName());
        }
        if (taskDto.getDescription() != null) {
            taskEntity.setDescription(taskDto.getDescription());
        }
    }

    @Override
    @Transactional
    public void deleteTask(UUID taskId) {
        TaskEntity taskEntity = Optional.ofNullable(
                        entityManager.find(TaskEntity.class, taskId)
                )
                .orElseThrow();

        taskEntity.setIsDeleted(true);
    }
}
