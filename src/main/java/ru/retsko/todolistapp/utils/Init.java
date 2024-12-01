package ru.retsko.todolistapp.utils;


import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.retsko.todolistapp.model.entities.Comment;
import ru.retsko.todolistapp.model.entities.Role;
import ru.retsko.todolistapp.model.entities.Task;
import ru.retsko.todolistapp.model.entities.User;
import ru.retsko.todolistapp.model.enums.TaskPriority;
import ru.retsko.todolistapp.model.enums.TaskStatus;
import ru.retsko.todolistapp.repositories.CommentRepository;
import ru.retsko.todolistapp.repositories.TaskRepository;
import ru.retsko.todolistapp.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Init {
    @PersistenceContext
    EntityManager entityManager;

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    public Init(UserRepository userRepository, CommentRepository commentRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    @EventListener(ApplicationStartedEvent.class)
    @Transactional
    public void init() throws IOException {
        Role role = Role.builder().name("ROLE_USER").build();
        entityManager.persist(role);
        User user = User
                .builder()
                .username("user")
                .password("$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i")
                .email("user@gmail.com")
                .build();
        user.setRoles(List.of(role));
        entityManager.persist(user);
        role = Role.builder().name("ROLE_ADMIN").build();
        entityManager.persist(role);

        user = User
                .builder()
                .username("admin")
                .password("$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i")
                .email("admin@gmail.com")
                .build();
        user.setRoles(List.of(role));
        entityManager.persist(user);


        for (long i = 0L; i < 20L; i++) {
            Task task = Task
                    .builder()
                    .title("Задача №" + i)
                    .start(LocalDateTime.now().plusDays((long) (1 + Math.random() * 20)))
                    // .end(LocalDateTime.now().plusDays((long) (1 + Math.random() * 20)))
                    .description("Описание для Задачи №" + i)
                    .executor(userRepository.findById((long) ((Math.random() * 2) + 1)).get())
                    .author(userRepository.findById((long) ((Math.random() * 2) + 1)).get())
                    .status(TaskStatus.getById((int) ((Math.random() * 2))))
                    .priority(TaskPriority.getById((int) ((Math.random() * 2))))
                    .build();
            entityManager.persist(task);
        }
        for (long i = 0L; i < 8L; i++) {
            Task task = Task
                    .builder()
                    .title("Выполненная Задача №" + i)
                    .start(LocalDateTime.now().plusDays((long) (1 + Math.random() * 20)))
                    // .end(LocalDateTime.now().plusDays((long) (1 + Math.random() * 20)))
                    .description("Описание для выполненной Задачи №" + i)
                    .executor(userRepository.findById((long) ((Math.random() * 2) + 1)).get())
                    .author(userRepository.findById((long) ((Math.random() * 2) + 1)).get())
                    .status(TaskStatus.getById((int) ((Math.random() * 2))))
                    .priority(TaskPriority.getById((int) ((Math.random() * 2))))
                    .build();
            entityManager.persist(task);
        }
        for (long i = 0L; i < 20L; i++) {
            Comment comment = Comment
                    .builder()
                    .description("Описание для Комментария №" + i)
                    .author(userRepository.findById((long) ((Math.random() * 2) + 1)).get())
                    .task(taskRepository.findById((long) (1 + Math.random() * 20)).get())

                    .build();
            entityManager.persist(comment);
        }
    }
//te
}
