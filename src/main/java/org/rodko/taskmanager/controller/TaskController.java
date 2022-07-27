package org.rodko.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.rodko.taskmanager.model.dto.TaskDto;
import org.rodko.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public void createTask(@RequestBody TaskDto taskDto){
        taskService.createTask(taskDto);
    }

    @GetMapping("/{taskId}")
    public TaskDto getTask(@PathVariable UUID taskId){
        return taskService.getTask(taskId);
    }

    @GetMapping
    public List<TaskDto> getTasks(){
        return taskService.getTasks();
    }

    @PutMapping("/{taskId}")
    public void updateTask(UUID taskId){
        taskService.updateTask(taskId);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(UUID taskId){
        taskService.deleteTask(taskId);
    }
}
