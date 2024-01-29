package com.example.KTElabs_test.dto.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class NewPatientDto {

    @NotBlank(message = "Ошибка валидации. Имя не может быть пустым")
    @Size(min = 1, max = 255, message = "Ошибка валидации. Имя не может быть короче 1 буквы или длинее 255")
    private String fullName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Дата дня рожденя не может быть пустой")
    private LocalDate birthday;

    @Email(message = "Ошибка валидации. Не верный формат электронной почты")
    @Size(max = 255, message = "Ошибка валидации. Почта не может быть длинее 255 символов")
    private String email;

    @NotBlank(message = "Ошибка валидации. Номер телефона не может быть пустой")
    private String phoneNumber;
}
