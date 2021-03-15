package com.gmail.dkylish.controller;

import com.gmail.dkylish.entity.Task;
import com.gmail.dkylish.service.TaskService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskRest {

    TaskService taskService;

    public TaskRest(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/column/{columnId}/task")
    public Task createTask(@PathVariable(value = "columnId") Long columnId,
                           @RequestBody Task task){

      return taskService.createTask(task);
    }

    @PutMapping("/column/{columnId}/task/{taskId}")
    public Task updateTask(@PathVariable(value = "taskId") Long taskId,
                           @PathVariable(value = "columnId") Long columnId,
                           @RequestBody Task task){

        return taskService.updateTask(taskId, task);
    }

    @DeleteMapping("/column/{columnId}/task/{taskId}")
    public Task deleteTask(@PathVariable(value = "taskId") Long taskId,
                           @PathVariable(value = "columnId") Long columnId){

        return taskService.deleteTask(taskId);
    }
}
