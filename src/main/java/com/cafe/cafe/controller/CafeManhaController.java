package com.cafe.cafe.controller;

import com.cafe.cafe.dto.CafeManhaDTO;
import com.cafe.cafe.service.CafeManhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cafemanha")
public class CafeManhaController {
    @Autowired
    private CafeManhaService cafeManhaService;

    @GetMapping("/lista")
    public List<CafeManhaDTO> getAll() {
        return this.cafeManhaService.getAllCafe();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CafeManhaDTO cafeManhaDTO) {
        this.cafeManhaService.createCafe(cafeManhaDTO);
    }
}
