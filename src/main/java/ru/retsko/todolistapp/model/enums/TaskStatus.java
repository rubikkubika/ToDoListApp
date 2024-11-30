package ru.retsko.todolistapp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatus {
    WAITING,
    INPROGRESS,
    COMPLETED;

    private static final TaskStatus[] values = TaskStatus.values();

    public static TaskStatus getById(int id) {
        return values[id];
    }
}
