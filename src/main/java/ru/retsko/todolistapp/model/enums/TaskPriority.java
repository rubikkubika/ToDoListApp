package ru.retsko.todolistapp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskPriority {
    LOW,
    MIDDLE,
    HIGH;
    private static final TaskPriority[] values = TaskPriority.values();

    public static TaskPriority getById(int id) {
        return values[id];
    }
}
