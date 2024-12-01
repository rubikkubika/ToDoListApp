package ru.retsko.todolistapp.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.retsko.todolistapp.model.entities.Task;
import ru.retsko.todolistapp.model.entities.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, PagingAndSortingRepository<Task, Long> {
    List<Task> getAllByExecutor(User executor, Pageable pageable);

    List<Task> findByExecutorAndDescriptionContains(User executor, Pageable pageable, String search);
}
