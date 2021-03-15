package com.gmail.dkylish.service.impl;

import com.gmail.dkylish.entity.Task;
import com.gmail.dkylish.persistance.TaskRepository;
import com.gmail.dkylish.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, Task updateTask) {
        Task taskById = taskRepository.getTaskById(taskId);
        taskById.setId(taskId);
        taskById.setTitle(updateTask.getTitle());
        taskById.setDescription(updateTask.getDescription());
        taskById.setColumns(updateTask.getColumns());
        return taskRepository.save(taskById);
    }

    @Override
    public Task deleteTask(Long taskId) {
        Task taskById = taskRepository.getTaskById(taskId);
        taskRepository.delete(taskById);
        return taskById;
    }
}
