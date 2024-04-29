package com.cafe.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CafeManhaResponseDTO {
    private Long id;

    private String nomeColaborador;

    private String cpfColaborador;

    private String cafe;

    private LocalDate data;

    private Boolean entrega;
}
