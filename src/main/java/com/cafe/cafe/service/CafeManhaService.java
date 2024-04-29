package com.cafe.cafe.service;

import com.cafe.cafe.dto.CafeManhaDTO;
import com.cafe.cafe.dto.CafeManhaResponseDTO;
import com.cafe.cafe.exception.BadRequestException;
import com.cafe.cafe.exception.ConflitException;
import com.cafe.cafe.exception.NoContentException;
import com.cafe.cafe.exception.NotfoundException;
import com.cafe.cafe.model.CafeManha;
import com.cafe.cafe.repository.CafeManhaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CafeManhaService {
    @Autowired
    private CafeManhaRepository cafeManhaRepository;

    private final ModelMapper mapper = new ModelMapper();

    public List<CafeManhaResponseDTO> getAllCafe() {
         List<CafeManhaResponseDTO> ListCafeManha = cafeManhaRepository.findAll().stream().map(cafeManha -> {
            return mapper.map(cafeManha, CafeManhaResponseDTO.class);
        }).toList();

        if (ListCafeManha.isEmpty()) {
            throw new NoContentException("Nenhum café da manhã cadastrado");
        }

        return ListCafeManha;
    }

    public CafeManhaResponseDTO getById(Long id) {
        CafeManha cafeManha = cafeManhaRepository.findById(id).orElseThrow(() -> new NotfoundException("Café da manhã não encontrado"));
        return mapper.map(cafeManha, CafeManhaResponseDTO.class);
    }

    public List<CafeManhaResponseDTO> getByDate(LocalDate data) {
        List<CafeManhaResponseDTO> listaCafePorData = cafeManhaRepository.findByData(data).stream()
                .map(cafeManha -> mapper.map(cafeManha, CafeManhaResponseDTO.class))
                .toList();

        if (listaCafePorData.isEmpty()) {
            throw new NoContentException("Nenhum café da manhã cadastrado para a data especificada");
        }

        return listaCafePorData;
    }

    public void createCafe(CafeManhaDTO cafeManhaDTO) {
        boolean valido = validarCPF(cafeManhaDTO.getCpfColaborador());
        if (!valido) {
            throw new BadRequestException("O CPF informado é inválido");
        }

        CafeManha cafeExisting = cafeManhaRepository.findByName(cafeManhaDTO.getCafe());
        if (cafeExisting != null) {
            if (cafeExisting.getCafe().equals(cafeManhaDTO.getCafe()) && cafeManhaDTO.getData() != cafeExisting.getData()) {
                throw new ConflitException("Já existe um café da manhã para esse dia");
            }
        }

        LocalDate dataAtual = LocalDate.now();
        if (!cafeManhaDTO.getData().isAfter(dataAtual)) {
            throw new BadRequestException("A data deve ser no dia seguinte ou posterior.");
        }

        CafeManha newCafeManha = mapper.map(cafeManhaDTO, CafeManha.class);
        newCafeManha.setCafe(cafeManhaDTO.getCafe().toLowerCase());
        cafeManhaRepository.save(newCafeManha);
    }

    public void updateCafe(Long id, CafeManhaDTO cafeManhaDTO){
        CafeManha cafeManha = cafeManhaRepository.findById(id)
                .orElseThrow(() -> new NotfoundException("Café da manhã não encontrado"));

        cafeManha.setNomeColaborador(cafeManhaDTO.getNomeColaborador());
        cafeManha.setCpfColaborador(cafeManhaDTO.getCpfColaborador());
        cafeManha.setCafe(cafeManhaDTO.getCafe());
        cafeManha.setData(cafeManhaDTO.getData());

        cafeManhaRepository.save(cafeManha);
    }

    public void deleteCafe(Long id) {
        CafeManha cafeManhaExisting = cafeManhaRepository.findById(id)
                .orElseThrow(() -> new NotfoundException("Café da manhã não encontrado"));

        cafeManhaRepository.deleteById(id);
    }

    public void updateEntregaCafe(Long id) {
        CafeManha cafeManha = cafeManhaRepository.findById(id)
                .orElseThrow(() -> new NotfoundException("Café da manhã não encontrado"));

        cafeManha.setEntrega(true);
        cafeManhaRepository.save(cafeManha);
    }

    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11)
            return false;

        boolean allSame = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                allSame = false;
                break;
            }
        }
        if (allSame)
            return false;

        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(cpf.charAt(i));
        }
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digits[i] * (10 - i);
        }
        int remainder = sum % 11;
        int digit1 = remainder < 2 ? 0 : 11 - remainder;
        if (digit1 != digits[9])
            return false;

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += digits[i] * (11 - i);
        }
        remainder = sum % 11;
        int digit2 = remainder < 2 ? 0 : 11 - remainder;
        if (digit2 != digits[10])
            return false;

        return true;
    }
}
