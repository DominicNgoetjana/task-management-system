package io.taskman.taskmanagementsystem.controller;

import io.taskman.taskmanagementsystem.persistence.entity.Task;
import io.taskman.taskmanagementsystem.service.TaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The TaskController class handles the API endpoints for tasks.
 */
@Log4j2
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Retrieves a list of all tasks.
     *
     * @return A list of Task objects representing all tasks.
     */
    @GetMapping
    public List<Task> getAllTasks() {
        log.info("Fetching all tasks");
        return taskService.getAllTasks();
    }

    /**
     * Creates a new task and saves it to the database.
     *
     * @param task The task object to be created.
     * @return A ResponseEntity object with the newly created task.
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        log.info("Creating a task with title: {}", task.getTitle());
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id The ID of the task to retrieve.
     * @return A ResponseEntity object containing the task with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {
        log.info("Retrieving a task with id: {}", id);
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * Updates a task with the given ID.
     *
     * @param id          The ID of the task to update.
     * @param taskDetails The updated task details.
     * @return A ResponseEntity object containing the updated task.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task taskDetails) {
        log.info("Updating a task with id: {}", id);
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id The ID of the task to delete.
     * @return A ResponseEntity with a success status code if the task is deleted successfully.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable long id) {
        log.info("Deleting a task with id: {}", id);
        taskService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }

}
