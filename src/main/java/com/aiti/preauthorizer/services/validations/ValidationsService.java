package com.aiti.preauthorizer.services.validations;

import com.aiti.preauthorizer.domain.app.TblCardsEntity;
import com.aiti.preauthorizer.domain.app.TblEnrollmentUsersEntity;

import java.util.Map;

public interface ValidationsService {
    TblCardsEntity validateUserAndCardExists(String issuerClientId, String pan , String codeError);
    TblCardsEntity validateUserExistsAndCardNotExists(String issuerClientId, String pan , String codeError);
    TblEnrollmentUsersEntity validateUserExists(String issuerClientId, String codeError);
    void validParamsRequestCard(String issuerClientId, String pan, String traceId, String codeError);
    void validParamsAndBodyRequestCard(String issuerClientId, String pan, String traceId, Map<String, Object> body, Integer typeBody, String codeError);

}
