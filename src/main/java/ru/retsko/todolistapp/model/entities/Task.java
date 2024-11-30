package ru.retsko.todolistapp.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import ru.retsko.todolistapp.model.enums.TaskPriority;
import ru.retsko.todolistapp.model.enums.TaskStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "tasks")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private TaskPriority priority;
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    @ManyToOne(fetch = FetchType.LAZY)
    private User executor;
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Comment> comments;
    @Column(name = "end_task")
    private LocalDateTime end;
    @Column(name = "start_task")
    private LocalDateTime start;
}
