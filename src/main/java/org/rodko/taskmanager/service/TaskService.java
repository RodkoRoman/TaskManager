package org.rodko.taskmanager.service;

import lombok.RequiredArgsConstructor;
import org.rodko.taskmanager.model.dto.TaskDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    public void createTask(TaskDto taskDto) {

    }

    public TaskDto getTask(UUID taskId) {
        return new TaskDto(
                UUID.randomUUID(),
                "Пойти за покупками",
                "Купить продукты для ужина",
                Instant.now(),
                false
        );
    }

    public List<TaskDto> getTasks() {
        return List.of(
                new TaskDto(
                        UUID.randomUUID(),
                        "Поездка к родителям",
                        "Навестить родителей на выходных",
                        Instant.now(),
                        false
                ),
                new TaskDto(
                        UUID.randomUUID(),
                        "Поход",
                        "Обсудить будующий поход с друзьями",
                        Instant.now(),
                        false
                )
        );
    }

    public void updateTask(UUID taskId){

    }

    public void deleteTask(UUID taskId){

    }
}
