package com.aiti.preauthorizer.services.postgres;

import com.aiti.preauthorizer.domain.app.TblCardsEntity;

import java.util.Map;

public interface MobileResponseService {
    Map<String, Object> generateSuccessResponseGet (TblCardsEntity cardEntity, String typeChannel, String traceId);
    Map<String, Object> generateSuccessResponseSetting (TblCardsEntity cardEntity, String typeChannel, Map<String, Object> amounts, String traceId);
}
