package ru.retsko.todolistapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.retsko.todolistapp.model.dtos.JwtRequest;
import ru.retsko.todolistapp.model.dtos.RegistrationUserDto;
import ru.retsko.todolistapp.services.AuthService;


@RestController
@RequiredArgsConstructor
@Tag(name = "Контроллер управления авторизацией и регистрацией")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Получение токена JWT")
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody(description = "Необходимо передать логин и пароль")
                                             @org.springframework.web.bind.annotation.RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @Operation(summary = "Регистрация нового пользователя")
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}
