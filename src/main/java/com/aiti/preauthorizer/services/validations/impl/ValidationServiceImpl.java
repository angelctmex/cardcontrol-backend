package com.aiti.preauthorizer.services.validations.impl;

import com.aiti.preauthorizer.domain.app.TblCardsEntity;
import com.aiti.preauthorizer.domain.app.TblEnrollmentUsersEntity;
import com.aiti.preauthorizer.dto.exceptions.RequestBadException;
import com.aiti.preauthorizer.dto.exceptions.RequestConflictException;
import com.aiti.preauthorizer.dto.exceptions.ResourceNotFoundException;
import com.aiti.preauthorizer.repository.EnrollmentsUsersRepository;
import com.aiti.preauthorizer.repository.TblCardsRepository;
import com.aiti.preauthorizer.services.validations.ValidationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ValidationServiceImpl implements ValidationsService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final List<String> keysAmounts = Collections.unmodifiableList(Arrays.asList(
            "atmAmount", "posAmount", "ecomerceAmount", "telephoneAmount", "internationAmount"));
    private static final List<String> keysMovimientes = Collections.unmodifiableList(Arrays.asList(
            "atm", "pos", "ecomerce", "telephone", "international"));
    private static  final  List<String> keyStatus= Collections.unmodifiableList(Arrays.asList(
            "atm", "pos", "ecomerce", "phone", "international", "status_pan"));

    @Autowired
    private EnrollmentsUsersRepository enrollmentsUsersRepository;

    @Autowired
    private TblCardsRepository cardsRepository;

    public TblCardsEntity validateUserAndCardExists(String issuerClientId, String pan , String codeError) throws ResourceNotFoundException {
        TblEnrollmentUsersEntity enrollUser = validateUserExists(issuerClientId,codeError);
        TblCardsEntity card = cardsRepository.findByPanAndIdUser(pan, enrollUser.getIdUser());

        if (card == null) {
            throw new ResourceNotFoundException("Id card not found", codeError);
        }

        return card;
    }

    public TblCardsEntity validateUserExistsAndCardNotExists(String issuerClientId, String pan , String codeError) {
        TblEnrollmentUsersEntity enrollUser = validateUserExists(issuerClientId,codeError);
        TblCardsEntity card = cardsRepository.findByPanAndIdUser(pan, enrollUser.getIdUser());
        if (card != null) {
            throw new RequestConflictException("card does exits", codeError);
        }
        return new TblCardsEntity();
    }
    public TblEnrollmentUsersEntity validateUserExists(String issuerClientId, String codeError) {
        TblEnrollmentUsersEntity enrollUser = enrollmentsUsersRepository.findByIssuerclientId(issuerClientId);
        if (enrollUser == null) {
            throw new ResourceNotFoundException("Id user not found", codeError);
        }
        return enrollUser;
    }

    public void validParamsRequestCard(String issuerClientId, String pan, String traceId, String codeError) {

        String message = validParamsHeader(issuerClientId, pan, traceId);

        if (!message.equals(""))
            throw new RequestBadException(message, codeError);

        log.debug("¡ Parametros header Correctos !");
    }

    public void validParamsAndBodyRequestCard(String issuerClientId, String pan, String traceId, Map<String, Object> body, Integer typeBody, String codeError) {

        validParamsRequestCard(issuerClientId, pan, traceId, codeError);

        String message = validRequestBody(body, typeBody);

        if (!message.equals(""))
            throw new RequestBadException(message, codeError);

        log.debug("¡ Parametros de BODY Correctos !");

    }

    private String validParamsHeader(String userId, String panCard, String traceId) {

        String msgError = "";

        if ( traceId == null ) {
            return "Missing request header 'traceId'. ";
        }

        if ( userId == null ) {
            return  "Missing request header 'userId'. ";
        }

        if (userId.trim().equals("") || userId.isEmpty())
            msgError = "Request header 'userId': can not be empty. ";

        if (panCard.trim().equals("") || panCard.isEmpty())
            msgError += "path variable idpan can not be empty. ";

        if (traceId.trim().equals("") || traceId.isEmpty())
            msgError += "Request header 'traceId' can not be empty. ";


        return msgError;
    }

    private String validRequestBody(Map<String, Object> body, int type) {

        if (body == null || body.isEmpty())
            return "Required request body content is missing";

        String msgError = "";
        List<String> keysNotFound = new ArrayList<>();
        List<String> keys = new ArrayList<>();

        switch (type) {
            case 1:
                keys = keysAmounts;
                break;
            case 2:
                keys = keysMovimientes;
                break;
            case 3:
                keys= keyStatus;
        }

        for (String key : keys) {
            if(!body.containsKey(key))
                keysNotFound.add(key);
        }

        if (keysNotFound.size() > 0) {
            msgError = "Missing fields to the body of the request: " + keysNotFound.toString();
            printLogMissingFields(msgError);
        }

        return msgError;
    }

    private void printLogMissingFields(String keysNotFound) {
        log.debug("<--------------------------------------------------");
        log.debug("Missing fields to the body of the request: " + keysNotFound);
        log.debug("<--------------------------------------------------");
    }
}
