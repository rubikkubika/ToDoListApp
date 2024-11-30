package ru.retsko.todolistapp.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.retsko.todolistapp.model.entities.Task;
import ru.retsko.todolistapp.model.entities.User;
import ru.retsko.todolistapp.repositories.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public List<Task> getAllTaskByExecutor(User executor, Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return taskRepository.getAllByExecutor(executor, pageable);
    }
    public  Task getTaskByiD(Long id){
        return taskRepository.findById(id).get();
    }
    public  void saveTask(Task task){
        taskRepository.save(task);
    }
}
