package ru.retsko.todolistapp.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.retsko.todolistapp.model.entities.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
