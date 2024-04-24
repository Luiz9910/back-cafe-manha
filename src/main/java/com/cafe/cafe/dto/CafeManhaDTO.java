package com.cafe.cafe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CafeManhaDTO {
    @NotBlank(message = "O campo café é obrigatório")
    private String cafe;

    @NotNull(message = "O campo data é obrigatório")
    private LocalDate data;
}
