package ru.retsko.todolistapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.retsko.todolistapp.model.entities.Task;
import ru.retsko.todolistapp.repositories.TaskRepository;

import java.util.List;

@RestController
@RequestMapping("/task/")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    List<Task> test() {

        return taskRepository.findAll();
    }
}
