package ru.tasks.logical.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на регистрацию")
public class SignUpRequest {

    @Schema(description = "Имя пользователя", example = "Иван")
    @Size(min = 2, max = 50, message = "Имя пользователя должно содержать от 2 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Иванов")
    @Size(min = 2, max = 50, message = "Фамилия пользователя должна содержать от 2 до 50 символов")
    @NotBlank(message = "Фамилия пользователя не может быть пустым")
    private String lastName;

    @Schema(description = "Адрес электронной почты", example = "ivanov@mail.ru")
    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустым")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @Schema(description = "Пароль", example = "my_secret_password")
    @Size(min = 5, max = 255, message = "Пароль должен содержать от 2 до 50 символов")
    private String password;
}
