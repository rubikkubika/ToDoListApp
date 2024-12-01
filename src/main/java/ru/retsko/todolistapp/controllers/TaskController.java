package ru.retsko.todolistapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.retsko.todolistapp.exceptions.NotFoundException;
import ru.retsko.todolistapp.exceptions.UnathorizatedException;
import ru.retsko.todolistapp.model.dtos.TaskDto;
import ru.retsko.todolistapp.model.entities.Role;
import ru.retsko.todolistapp.model.entities.Task;
import ru.retsko.todolistapp.model.entities.User;
import ru.retsko.todolistapp.repositories.TaskRepository;
import ru.retsko.todolistapp.services.TaskService;
import ru.retsko.todolistapp.services.UserService;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/task")
@Tag(name = "Контроллер управления задачами")
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskRepository taskRepository, TaskService taskService, UserService userService) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.userService = userService;
    }

    @Operation(summary = "Получение списка задач по Исполнителю")
    @GetMapping("/executor")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getAllTaskByUserName(@Parameter(description = "Имя исполнителя") @RequestParam(name = "executorname") String executorName,
                                           @Parameter(description = "Поиск по описанию") @RequestParam(name = "search", defaultValue = "", required = false) String search,
                                           @Parameter(description = "Номер страницы.") @RequestParam(name = "offset", defaultValue = "0", required = false) Integer offset,
                                           @Parameter(description = "Кол-во элементов на странице.") @RequestParam(name = "limit", defaultValue = "5", required = false) Integer limit,
                                           Principal principal) {
        if (principal == null) {
            throw new UnathorizatedException("Требуется Авторизация");
        }
        User executor = userService.findByUsername(executorName).orElseThrow(() -> new NotFoundException("Исполнитель не найден"));
        User principalUser = userService.findByUsername(principal.getName()).orElseThrow(() -> new NotFoundException("Исполнитель не найден"));
        Collection<Role> principalUserRolesList = principalUser.getRoles();
        if (!Objects.equals(executorName, principal.getName()) && principalUserRolesList.stream().noneMatch(role -> Objects.equals(role.getName(), "ROLE_ADMIN"))) {
            throw new UnathorizatedException("Вы можете просматривать только свои задачи");
        }
        if (!Objects.equals(search, "")) {
            return taskService.getAllTaskByExecutorDescriptionContains(executor, offset, limit, search);
        }
        return taskService.getAllTaskByExecutor(executor, offset, limit);
    }

    @Operation(summary = "Изменение задачи по исполнителю")
    @PostMapping("/change")
    @Produces(MediaType.APPLICATION_JSON)
    public Object changeTask(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Необходимо передать Задачу с id и полями для изменения") @Valid @RequestBody TaskDto taskDto, Principal principal) {
        if (principal == null) {
            throw new UnathorizatedException("Требуется Авторизация");
        }
        Task task = taskService.getTaskByiD(taskDto.getId());
        User principalUser = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Исполнитель не найден"));

        boolean isAdmin = principalUser.getRoles().stream()
                .anyMatch(role -> "ROLE_ADMIN".equals(role.getName()));

        if (isAdmin) {
            if (taskDto.getDescription() != null) task.setDescription(taskDto.getDescription());
            if (taskDto.getStatus() != null) task.setStatus(taskDto.getStatus());
            if (taskDto.getTitle() != null) task.setTitle(taskDto.getTitle());
            if (taskDto.getPriority() != null) task.setPriority(taskDto.getPriority());
            if (taskDto.getExecutor() != null) task.setExecutor(taskDto.getExecutor());
        } else {
            if (!Objects.equals(task.getExecutor().getUsername(), principal.getName())) {
                throw new UnathorizatedException("Вы можете Редактировать только свои задачи");
            }
            if (taskDto.getStatus() != null) {
                task.setStatus(taskDto.getStatus());

            }
            taskService.saveTask(task);
        }
        return task;
    }
}
