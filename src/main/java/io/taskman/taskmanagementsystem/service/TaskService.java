package io.taskman.taskmanagementsystem.service;

import io.taskman.taskmanagementsystem.persistence.entity.Task;
import io.taskman.taskmanagementsystem.persistence.repo.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dominic Ngoetjana on 2024/08/03.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTaskById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void deleteTaskById(long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);
        task.setTitle(task.getTitle());
        task.setDescription(task.getDescription());
        task.setDueDate(task.getDueDate());
        task.setStatus(task.getStatus());
        return taskRepository.save(task);
    }
}
