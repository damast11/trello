package com.gmail.dkylish.service;

import com.gmail.dkylish.entity.Task;

/**
 * Service for tasks.
 */
public interface TaskService {

    /**
     * Create Task for specific column id
     * @param task task
     * @return created task with column id
     */
    Task createTask(Task task);

    /**
     * Update Task by id
     * @param taskId task Id
     * @param updateTask update Task
     * @return updated task with column id
     */
    Task updateTask(Long taskId, Task updateTask);

    /**
     * Delete Task by id
     * @param taskId task Id
     * @return delete task
     */
    Task deleteTask(Long taskId);
}
