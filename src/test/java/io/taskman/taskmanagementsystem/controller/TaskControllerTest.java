package io.taskman.taskmanagementsystem.controller;

import io.taskman.taskmanagementsystem.persistence.entity.Task;
import io.taskman.taskmanagementsystem.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Created by Dominic Ngoetjana on 2024/08/03.
 */
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getAllTasks() {
        Task task1 = new Task("Task 1", "Description 1", LocalDateTime.now(), Task.TaskStatus.TODO);
        Task task2 = new Task("Task 2", "Description 2", LocalDateTime.now(), Task.TaskStatus.IN_PROGRESS);
        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskService.getAllTasks()).thenReturn(tasks);

        List<Task> result = taskController.getAllTasks();

        assertEquals(2, result.size());
        assertEquals(task1, result.get(0));
        assertEquals(task2, result.get(1));
    }

    @Test
    void createTask() {
        Task task = new Task("New Task", "New Description", LocalDateTime.now(), Task.TaskStatus.TODO);

        when(taskService.createTask(any(Task.class))).thenReturn(task);

        ResponseEntity<Task> response = taskController.createTask(task);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    void getTaskById() {
        long taskId = 1L;
        Task task = new Task("Task 1", "Description 1", LocalDateTime.now(), Task.TaskStatus.TODO);
        task.setId(taskId);

        when(taskService.getTaskById(taskId)).thenReturn(task);

        ResponseEntity<Task> response = taskController.getTaskById(taskId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    void updateTask() {
        Long taskId = 1L;
        Task updatedTask = new Task("Updated Task", "New Description", LocalDateTime.now(), Task.TaskStatus.IN_PROGRESS);
        updatedTask.setId(taskId);

        when(taskService.updateTask(eq(taskId), any(Task.class))).thenReturn(updatedTask);

        ResponseEntity<Task> response = taskController.updateTask(taskId, updatedTask);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTask, response.getBody());
    }

    @Test
    void deleteTask() {
        long taskId = 1L;

        ResponseEntity<Task> response = taskController.deleteTask(taskId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(taskService, times(1)).deleteTaskById(taskId);
    }

}