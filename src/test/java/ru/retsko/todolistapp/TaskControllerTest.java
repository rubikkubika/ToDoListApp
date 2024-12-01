package ru.retsko.todolistapp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.retsko.todolistapp.model.entities.Role;
import ru.retsko.todolistapp.model.entities.Task;
import ru.retsko.todolistapp.model.entities.User;
import ru.retsko.todolistapp.services.TaskService;
import ru.retsko.todolistapp.services.UserService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private TaskService taskService;

    @Test
    void testGetAllTaskByUserName() throws Exception {

        String executorName = "user";
        String principalName = "admin";
        String search = "";
        int offset = 0;
        int limit = 5;

        User executor = new User();
        executor.setUsername(executorName);

        User principalUser = new User();
        principalUser.setUsername(principalName);
        principalUser.setRoles(List.of(new Role("ROLE_ADMIN")));

        List<Task> tasks = List.of(new Task("Task 1"), new Task("Task 2"));

        Mockito.when(userService.findByUsername(executorName)).thenReturn(Optional.of(executor));
        Mockito.when(userService.findByUsername(principalName)).thenReturn(Optional.of(principalUser));
        Mockito.when(taskService.getAllTaskByExecutor(executor, offset, limit)).thenReturn(tasks);


        mockMvc.perform(get("/task/executor")
                        .param("executorname", executorName)
                        .param("search", search)
                        .param("offset", String.valueOf(offset))
                        .param("limit", String.valueOf(limit))
                        .with(user(principalName))) // Симулируем Principal
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[1].title").value("Task 2"));
        Mockito.verify(userService).findByUsername(executorName);
        Mockito.verify(userService).findByUsername(principalName);
        Mockito.verify(taskService).getAllTaskByExecutor(executor, offset, limit);
    }
}
