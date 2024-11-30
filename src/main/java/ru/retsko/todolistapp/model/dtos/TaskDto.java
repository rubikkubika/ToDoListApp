package ru.retsko.todolistapp.model.dtos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.retsko.todolistapp.model.entities.Comment;
import ru.retsko.todolistapp.model.entities.User;
import ru.retsko.todolistapp.model.enums.TaskPriority;
import ru.retsko.todolistapp.model.enums.TaskStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = TaskDto.class)
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private User author;
    private User executor;
    private List<Comment> comments;
    private LocalDateTime end;
    private LocalDateTime start;
}
