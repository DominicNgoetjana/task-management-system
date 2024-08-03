package io.taskman.taskmanagementsystem.service;

import io.taskman.taskmanagementsystem.persistence.entity.Task;
import io.taskman.taskmanagementsystem.persistence.entity.Task.TaskStatus;
import io.taskman.taskmanagementsystem.persistence.repo.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Dominic Ngoetjana on 2024/08/03.
 */
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTasks() {
        Task task1 = new Task("Task 1", "Description 1", LocalDateTime.now(), TaskStatus.TODO);
        Task task2 = new Task("Task 2", "Description 2", LocalDateTime.now(), TaskStatus.IN_PROGRESS);
        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        assertEquals(task1, result.get(0));
        assertEquals(task2, result.get(1));
    }

    @Test
    void createTask() {
        Task task = new Task("New Task", "New Description", LocalDateTime.now(), TaskStatus.TODO);

        when(taskRepository.save(ArgumentMatchers.any())).thenReturn(task);

        Task result = taskService.createTask(task);

        assertEquals(task, result);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void getTaskById() {
        long taskId = 1L;
        Task task = new Task("Task 1", "Description 1", LocalDateTime.now(), TaskStatus.TODO);
        task.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(taskId);

        assertEquals(task, result);
    }

    @Test
    void getTaskById_notFound() {
        long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> taskService.getTaskById(taskId));
    }

    @Test
    void updateTask() {
        long taskId = 1L;
        Task existingTask = new Task("Existing Task", "Old Description", LocalDateTime.now(), TaskStatus.TODO);
        existingTask.setId(taskId);

        Task updatedTask = new Task("Updated Task", "New Description", LocalDateTime.now(), TaskStatus.IN_PROGRESS);
        updatedTask.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(ArgumentMatchers.any(Task.class))).thenReturn(updatedTask);

        Task result = taskService.updateTask(taskId, updatedTask);

        assertEquals(updatedTask, result);
        verify(taskRepository, times(1)).save(ArgumentMatchers.any(Task.class));
    }

    @Test
    void deleteTask() {
        long taskId = 1L;
        Task task = new Task("Task to Delete", "Description", LocalDateTime.now(), TaskStatus.TODO);
        task.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        taskService.deleteTaskById(taskId);

        verify(taskRepository, times(1)).delete(task);
    }

}