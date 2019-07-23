package com.aiti.preauthorizer.repository;

import com.aiti.preauthorizer.domain.app.CatBinesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatBinRepository extends JpaRepository<CatBinesEntity,String> {
    public CatBinesEntity findByBin(String bin);
}
