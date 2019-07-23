package com.aiti.preauthorizer.repository;

import com.aiti.preauthorizer.domain.app.CatBinesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatBinesRepository extends JpaRepository<CatBinesEntity, Long> {
    CatBinesEntity findByIdBin(long idBin);
}
