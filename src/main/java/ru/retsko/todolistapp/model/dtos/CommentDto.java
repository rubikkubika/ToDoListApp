package ru.retsko.todolistapp.model.dtos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.retsko.todolistapp.model.entities.User;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "taskId", scope = CommentDto.class)
public class CommentDto {
    private Long id;
    private String authorName;
    private String description;
    private Long taskId;
}
