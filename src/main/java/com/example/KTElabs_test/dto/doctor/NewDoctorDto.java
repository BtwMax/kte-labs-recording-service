package com.example.KTElabs_test.dto.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class NewDoctorDto {

    @NotBlank(message = "Ошибка валидации. Имя не может быть пустым")
    @Size(min = 1, max = 255, message = "Ошибка валидации. Имя не может быть короче 1 буквы или длинее 255")
    private String fullName;

    @NotBlank(message = "Ошибка валидации. Специальность не может быть пустой")
    @Size(min = 1, max = 155, message = "Ошибка валидации. Специальность не может быть короче 1 буквы или длинее 155")
    private String speciality;
}
