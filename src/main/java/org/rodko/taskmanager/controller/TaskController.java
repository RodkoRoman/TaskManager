package org.rodko.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.rodko.taskmanager.model.dto.TaskDto;
import org.rodko.taskmanager.model.entity.TaskEntity;
import org.rodko.taskmanager.service.TaskService;
import org.rodko.taskmanager.service.impl.RepositoryTaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskEntity createTask(@RequestBody TaskDto taskDto) {
        return taskService.createTask(taskDto);
    }

    @GetMapping("/{taskId}")
    public TaskDto getTask(@PathVariable UUID taskId) {
        return taskService.getTask(taskId);
    }

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskService.getTasks();
    }

    @PutMapping("/{taskId}")
    public void updateTask(@PathVariable UUID taskId, @RequestBody TaskDto taskDto) {
        taskService.updateTask(taskId, taskDto);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
    }
}
