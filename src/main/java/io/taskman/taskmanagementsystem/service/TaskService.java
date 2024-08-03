package io.taskman.taskmanagementsystem.service;

import io.taskman.taskmanagementsystem.persistence.entity.Task;
import io.taskman.taskmanagementsystem.persistence.repo.TaskRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dominic Ngoetjana on 2024/08/03.
 */
@Log4j2
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Retrieves a list of all tasks.
     *
     * @return A list of Task objects representing all tasks.
     */
    public List<Task> getAllTasks() {
        log.debug("Service::get all tasks");
        return taskRepository.findAll();
    }

    /**
     * Creates a new task and saves it to the database.
     *
     * @param task The task object to be created.
     * @return The newly created task object.
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id The ID of the task to retrieve.
     * @return The Task object with the specified ID.
     * @throws RuntimeException if task with the specified ID is not found.
     */
    public Task getTaskById(long id) {
        log.debug("Service::get by Id invoked with id {}", id);
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id The ID of the task to delete.
     */
    public void deleteTaskById(long id) {
        log.debug("Service::delete invoked with id {}", id);
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    /**
     * Updates a task with the given ID.
     *
     * @param id          The ID of the task to update.
     * @param taskDetails The updated task details.
     * @return The updated task object.
     * @throws RuntimeException if task with the specified ID is not found.
     */
    public Task updateTask(Long id, Task taskDetails) {
        log.debug("Service::update invoked with id {} and {}", id, taskDetails);
        Task task = getTaskById(id);
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setDueDate(taskDetails.getDueDate());
        task.setStatus(taskDetails.getStatus());
        return taskRepository.save(task);
    }

}
