package com.cafe.cafe.repository;

import com.cafe.cafe.model.CafeManha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CafeManhaRepository extends JpaRepository<CafeManha, Long> {
    @Query(nativeQuery = true, value = "SElECT * FROM cafemanha WHERE cafe = ?")
    CafeManha findByName(String name);

    @Query(value = "SELECT * FROM cafemanha WHERE data = ?", nativeQuery = true)
    List<CafeManha> findByData(LocalDate data);
}
