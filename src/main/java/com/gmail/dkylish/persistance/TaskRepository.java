package com.gmail.dkylish.persistance;

import com.gmail.dkylish.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {

    Task getTaskById(Long id);
}
