package com.aiti.preauthorizer.repository;

import com.aiti.preauthorizer.domain.app.TblCardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TblCardsRepository extends JpaRepository<TblCardsEntity, Long> {
    TblCardsEntity findByPan (String pan);
    TblCardsEntity findByPanAndIdUser (String pan, Long idUser);
    List<TblCardsEntity> findAllByIdUser(Long idClient);
}
