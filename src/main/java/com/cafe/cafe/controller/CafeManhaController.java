package com.cafe.cafe.controller;

import com.cafe.cafe.dto.CafeManhaDTO;
import com.cafe.cafe.dto.CafeManhaResponseDTO;
import com.cafe.cafe.service.CafeManhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cafemanha")
public class CafeManhaController {
    @Autowired
    private CafeManhaService cafeManhaService;

    @CrossOrigin
    @GetMapping("{id}")
    public CafeManhaResponseDTO getById(@PathVariable Long id) {
        return this.cafeManhaService.getById(id);
    }

    @CrossOrigin
    @GetMapping("/lista")
    public List<CafeManhaResponseDTO> getAll() {
        return this.cafeManhaService.getAllCafe();
    }

    @CrossOrigin
    @GetMapping("/por-data")
    public List<CafeManhaResponseDTO> getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return this.cafeManhaService.getByDate(data);
    }

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CafeManhaDTO cafeManhaDTO) {
        this.cafeManhaService.createCafe(cafeManhaDTO);
    }

    @CrossOrigin
    @PutMapping("{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody CafeManhaDTO cafeManhaDTO) {
        this.cafeManhaService.updateCafe(id, cafeManhaDTO);
    }

    @CrossOrigin
    @PatchMapping("{id}")
    public void update(@PathVariable Long id) {
        this.cafeManhaService.updateEntregaCafe(id);
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.cafeManhaService.deleteCafe(id);
    }

}
