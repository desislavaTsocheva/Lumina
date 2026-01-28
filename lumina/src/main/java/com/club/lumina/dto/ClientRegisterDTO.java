package com.club.lumina.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRegisterDTO {
    @NotBlank(message = "Потребителското име е задължително поле")
    @Size(min = 4, max = 20, message = "Потребителското име трябва да е между 4 и 20 символа")
    private String username;

    @NotBlank(message = "Името е задължително поле")
    private String firstName;

    @NotBlank(message = "Фамилията е задължително поле")
    private String lastName;

    @Email(message = "Въведете валиден имейл адрес")
    @NotBlank(message = "Имейлът е задължително поле")
    private String email;

    @Min(value = 18, message = "Трябва да имате навършени 18 години")
    private Integer age;

    @Size(min = 10, max = 10, message = "Телефонният номер трябва да е точно 10 цифри")
    private String phoneNumber;

    @Size(min = 8, message = "Паролата трябва да е поне 8 символа")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$",
            message = "Паролата трябва да съдържа поне една цифра, една малка и една главна буква")
    private String password;
}