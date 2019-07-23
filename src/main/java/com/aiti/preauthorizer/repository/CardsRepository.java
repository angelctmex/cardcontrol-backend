package com.aiti.preauthorizer.repository;

import com.aiti.preauthorizer.domain.app.TblCardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zeus on 10/6/16.
 */
public interface CardsRepository extends JpaRepository<TblCardsEntity, String> {
    TblCardsEntity findByPanAndIdUser (String pan, long idUser);
}
