package com.aiti.preauthorizer.services.postgres.impl;

import com.aiti.preauthorizer.domain.app.TblCardsEntity;
import com.aiti.preauthorizer.domain.app.TblEnrollmentUsersEntity;
import com.aiti.preauthorizer.dto.GenericErrorMobileResponse;
import com.aiti.preauthorizer.dto.exceptions.RequestBadException;
import com.aiti.preauthorizer.dto.exceptions.ResourceNotFoundException;
import com.aiti.preauthorizer.repository.EnrollmentsUsersRepository;
import com.aiti.preauthorizer.repository.TblCardsRepository;
import com.aiti.preauthorizer.services.postgres.CardControlService;
import com.aiti.preauthorizer.services.postgres.MobileResponseService;
import com.aiti.preauthorizer.services.validations.ValidationsService;
import com.aiti.preauthorizer.utils.ConstantsRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Service
public class CardControlServiceImpl implements CardControlService {

    @Autowired
    private TblCardsRepository tblCardsRepository;

    @Autowired
    private EnrollmentsUsersRepository tblEnrollmentUsersRepo;

    @Autowired
    private MobileResponseService mobileResponseService;

    @Autowired
    private ValidationsService validationsService;

    /// roland
    /**
     * los datos de entrada para configurar los canales de la tarjeta
     * @param channels
     * @return resp
     */
    public Map configureCardAccessChannels(String pan,String userId,String traceid,Map channels) {
        Map<String, Object> response;
        validationsService.validParamsAndBodyRequestCard(userId,pan,traceid,channels,3,ConstantsRest.PUT_STATUS_BAD_REQUEST);
        TblCardsEntity cardEntity = validationsService.validateUserAndCardExists(userId,pan,ConstantsRest.PUT_STATUS_NOT_FOUND);

        return response = mobileResponseService.generateSuccessResponseSetting(cardEntity, "status", channels, traceid);
    }

    public Map configureNumberTransactionsDay(String userId, String panCard, String traceid, Map<String, Object> movimientes) {

        Map<String, Object> response;

        validationsService.validParamsAndBodyRequestCard(userId, panCard, traceid, movimientes, 2, ConstantsRest.PUT_MOVE_BAD_REQUEST);
        TblCardsEntity card = validationsService.validateUserAndCardExists(userId, panCard, ConstantsRest.PUT_MOVE_NOT_FOUND);

        response = mobileResponseService.generateSuccessResponseSetting(card, "channels", movimientes, traceid);

        return response;

    }

    /// roland
    /**
     * optener el status actual de los canales
     * @param pan el de la tarjeta
     * @return
     */
    public Map getCurrentStatusAccessChannels(String pan,String traceid,String userId) {
        validationsService.validParamsRequestCard(userId,pan,traceid,ConstantsRest.PUT_STATUS_BAD_REQUEST);
        TblCardsEntity cardEntity = validationsService.validateUserAndCardExists(userId,pan,ConstantsRest.PUT_STATUS_NOT_FOUND);
        return mobileResponseService.generateSuccessResponseGet(cardEntity, "status", traceid);
    }

    public Map getLimitAmountsOperation(String userId, String panCard, String traceid) {

        Map<String, Object> response;

        validationsService.validParamsRequestCard(userId, panCard, traceid, ConstantsRest.GET_CHNN_BAD_REQUEST);
        TblCardsEntity card = validationsService.validateUserAndCardExists(userId, panCard, ConstantsRest.GET_CHNN_NOT_FOUND);

        response = mobileResponseService.generateSuccessResponseGet(card, "operation", traceid);

        return response;
    }

    public Map getLimitAmountsDay(String userId, String panCard, String traceid) {

        Map<String, Object> responses;

        validationsService.validParamsRequestCard(userId, panCard, traceid, ConstantsRest.GET_CHNN_BAD_REQUEST);
        TblCardsEntity card = validationsService.validateUserAndCardExists(userId, panCard, ConstantsRest.GET_CHNN_NOT_FOUND);

        responses = mobileResponseService.generateSuccessResponseGet(card, "amounts", traceid);

        return responses;
    }

    public Map getNumberTransactionsDay(String userId, String panCard, String traceid) {

        Map<String, Object> response;

        validationsService.validParamsRequestCard(userId, panCard, traceid, ConstantsRest.GET_MOVE_BAD_REQUEST);
        TblCardsEntity card = validationsService.validateUserAndCardExists(userId, panCard, ConstantsRest.GET_MOVE_NOT_FOUND);

        response = mobileResponseService.generateSuccessResponseGet(card, "channels", traceid);

        return response;
    }

    public Map setMaximAmountsDay(String userId, String panCard, Map<String, Object> amounts, String traceid) throws ResourceNotFoundException, RequestBadException {

        Map<String, Object> response;

        validationsService.validParamsAndBodyRequestCard(userId, panCard, traceid, amounts, 1, ConstantsRest.PUT_CHNN_BAD_REQUEST);
        TblCardsEntity card = validationsService.validateUserAndCardExists(userId, panCard, ConstantsRest.PUT_CHNN_NOT_FOUND);

        response = mobileResponseService.generateSuccessResponseSetting(card, "amounts", amounts, traceid);

        return response;
    }

    public Map setMaximAmountsOperation(String userId, String panCard, Map<String, Object> amounts, String traceId) throws ResourceNotFoundException, RequestBadException {

        Map<String, Object> response;

        validationsService.validParamsAndBodyRequestCard(userId, panCard, traceId, amounts, 1, ConstantsRest.PUT_CHNN_BAD_REQUEST);
        TblCardsEntity card = validationsService.validateUserAndCardExists(userId, panCard, ConstantsRest.PUT_CHNN_NOT_FOUND);

        response = mobileResponseService.generateSuccessResponseSetting(card, "operation", amounts, traceId);

        return response;
    }

    private boolean resourceDoesNotExist(TblCardsEntity cardsEntity) {
        return cardsEntity == null;
    }

}
