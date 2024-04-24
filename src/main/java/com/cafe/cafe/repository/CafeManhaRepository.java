package com.cafe.cafe.repository;

import com.cafe.cafe.model.CafeManha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeManhaRepository extends JpaRepository<CafeManha, Long> {
}
