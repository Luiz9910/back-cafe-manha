package com.cafe.cafe.service;

import com.cafe.cafe.dto.CafeManhaDTO;
import com.cafe.cafe.exception.NoContentException;
import com.cafe.cafe.exception.NotfoundException;
import com.cafe.cafe.model.CafeManha;
import com.cafe.cafe.repository.CafeManhaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CafeManhaService {
    @Autowired
    private CafeManhaRepository cafeManhaRepository;
    private final ModelMapper mapper = new ModelMapper();

    public List<CafeManhaDTO> getAllCafe() {
         List<CafeManhaDTO> ListCafeManha = cafeManhaRepository.findAll().stream().map(cafeManha -> {
            return mapper.map(cafeManha, CafeManhaDTO.class);
        }).toList();

        if (ListCafeManha.isEmpty()) {
            throw new NoContentException("Nenhum café da manhã cadastrado");
        }

        return ListCafeManha;
    }

    public CafeManhaDTO getById(Long id) {
        CafeManha cafeManha = cafeManhaRepository.findById(id).orElseThrow(() -> new NotfoundException("Café da manhã não encontrado"));
        return mapper.map(cafeManha, CafeManhaDTO.class);
    }

    public void createCafe(CafeManhaDTO cafeManhaDTO) {
        CafeManha newCafeManha = mapper.map(cafeManhaDTO, CafeManha.class);
        cafeManhaRepository.save(newCafeManha);
    }
}
