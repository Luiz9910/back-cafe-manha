package com.cafe.cafe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cafemanha")
@Entity
public class CafeManha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cafe;

    @Column(name = "sale_date", nullable = false)
    private LocalDate data;
}
