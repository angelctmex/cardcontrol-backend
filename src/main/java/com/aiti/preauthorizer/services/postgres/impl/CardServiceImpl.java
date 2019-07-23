package com.aiti.preauthorizer.services.postgres.impl;

import com.aiti.preauthorizer.domain.app.CatBinesEntity;
import com.aiti.preauthorizer.domain.app.CatConfigEntity;
import com.aiti.preauthorizer.domain.app.TblEnrollmentUsersEntity;
import com.aiti.preauthorizer.dto.GenericErrorMobileResponse;
import com.aiti.preauthorizer.dto.exceptions.RequestBadException;
import com.aiti.preauthorizer.dto.exceptions.RequestConflictException;
import com.aiti.preauthorizer.dto.exceptions.ResourceNotFoundException;
import com.aiti.preauthorizer.repository.*;
import com.aiti.preauthorizer.services.postgres.CardService;
import com.aiti.preauthorizer.services.validations.ValidationsService;
import com.aiti.preauthorizer.utils.ConstantsRest;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.*;

import com.aiti.preauthorizer.domain.app.TblCardsEntity;

@Service
public class CardServiceImpl implements CardService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CardsRepository cardsRepository;

    @Autowired
    EnrollmentsUsersRepository tblEnrolementsUsersRepository;

    @Autowired
    TblCardsRepository tblCardsRepository;

    @Autowired
    ConfigCardRepository configCardRepository;

    @Autowired
    CatBinRepository catBinRepository;

    @Autowired
    private ValidationsService validationsService;


    Map rsponse = new HashMap();

    private String keysNotFounds = "";
    private String fielsEmpty = "";

    public Map blockCard(String pan,String idClient, String traceid,  int  status)throws RequestConflictException
    {
        log.debug("Block card");
        validationsService.validateUserAndCardExists(idClient,pan,ConstantsRest.PUT_STATUS_NOT_FOUND);

        TblCardsEntity card = validationsService.validateUserAndCardExists(idClient,pan,ConstantsRest.PUT_CARD_NOT_FOUND);

        Map resp = new HashMap();
        Map cardResp = new HashMap();
        try {
            Timestamp dateChage = new Timestamp(System.currentTimeMillis());
            switch (status){
                case 0:
                    card.setStatusPan(false);
                    cardResp.put("status","Desable card: thi is disable");
                    card.setDateUpdate(dateChage);
                    break;
                case 1:
                    card.setStatusPan(true);
                    cardResp.put("status","Change user's status: card is enabled");
                    break;
                default:
                    throw new RequestConflictException("This status not valid",
                            ConstantsRest.PUT_STATUS_BAD_REQUEST);

            }

            TblCardsEntity updatedCardEntity = tblCardsRepository.save(card);

            cardResp.put("usermask", card.getMaskcard());

            resp.put("message", "SUCCESS");
            resp.put("status", "OK");
            resp.put("Card", cardResp);
            return resp;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            GenericErrorMobileResponse errorRespons = new GenericErrorMobileResponse();
            errorRespons.setMessage(ex.getMessage());
            return errorRespons.getResponse();
        }
    }

    public Map blockUser(String iduser, String traceid, String status) throws ResourceNotFoundException, RequestBadException,RequestConflictException
    {
        Map resp = new HashMap();
        try {
            int Sta = 0;
            if(!isNumeric(status)){
                throw new RequestBadException("Bad Parameter: status",
                        ConstantsRest.PUT_STATUS_BAD_REQUEST);
            }
            Sta=Integer.parseInt(status);
            TblEnrollmentUsersEntity userEnable = tblEnrolementsUsersRepository.findByIssuerclientId(iduser);

            Map user=new HashMap();
            if (userDoesExist(userEnable)) {
                if(userEnable.getStatus()==3){
                    throw new RequestConflictException("This user: " + iduser + " was already definitely disabled",
                            ConstantsRest.PUT_STATUS_BAD_REQUEST);
                }
                Date dateChage = new Timestamp(System.currentTimeMillis());
                java.sql.Date sqlDate = new java.sql.Date(dateChage.getTime());
                LocalTime now = LocalTime.now();
                Time time = Time.valueOf( now );

                switch (Sta){
                    case 1:
                        userEnable.setStatus(Sta);
                        userEnable.setSysdate(sqlDate);
                        userEnable.setSystime(time);
                        user.put("status","Desable user: the user is enabled");
                        break;
                    case 2:
                        userEnable.setStatus(Sta);
                        userEnable.setSysdate(sqlDate);
                        userEnable.setSystime(time);
                        user.put("status","Change user's status: the user is disable");
                        break;
                    case 3:
                        userEnable.setStatus(Sta);
                        userEnable.setSysdate(sqlDate);
                        userEnable.setSystime(time);
                      List<TblCardsEntity> cards= getAllCards(iduser,traceid);
                      for(TblCardsEntity cardItem:cards){
                          blockCardM(cardItem);
                      }
                        user.put("status","the user is already definitely disabled");
                        break;
                    default:
                        throw new RequestConflictException("Bad Status",
                                ConstantsRest.PUT_STATUS_BAD_REQUEST);

                }
                TblEnrollmentUsersEntity uodatedUserEntity=tblEnrolementsUsersRepository.save(userEnable);


                user.put("usermask",userEnable.getUserNameMask());
                resp.put("message", "SUCCESS");
                resp.put("status", "ok");
                resp.put("user",user);
            }
            else{
                throw new RequestConflictException("This user: "+iduser+" does not exist",ConstantsRest.PUT_STATUS_NOT_FOUND);
            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            GenericErrorMobileResponse errorRespons = new GenericErrorMobileResponse();
            errorRespons.setMessage(ex.getMessage());
            return errorRespons.getResponse();
        }
        System.out.println(resp);
        return resp;
    }

    public Map enrollCard(String idClient, String traceid, Map dataEnroll)
    {

        validationsService.validParamsAndBodyRequestCard(idClient,dataEnroll.get("pan").toString(),traceid,dataEnroll,4,ConstantsRest.POST_ENROLL_CONFLICT);

        TblCardsEntity cardsEntity = validationsService.validateUserExistsAndCardNotExists(idClient,dataEnroll.get("pan").toString(),ConstantsRest.POST_ENROLL_CONFLICT);

        TblEnrollmentUsersEntity enrollUser = validationsService.validateUserExists(idClient,ConstantsRest.GET_USER_NOT_FOUND);

        CatBinesEntity catBines = catBinRepository.findByBin(dataEnroll.get("bin").toString());
        if(catBinDoesNotExist(catBines)){
            throw new ResourceNotFoundException("This bin : " + dataEnroll.get("bin").toString() + " does't exits",ConstantsRest.POST_ENROLL_CONFLICT);
        }
        Timestamp dateStamp=new Timestamp(System.currentTimeMillis());
        TblCardsEntity cardNew = new TblCardsEntity();
        cardNew.setPan(dataEnroll.get("pan").toString());
        cardNew.setMaskcard(dataEnroll.get("maskpan").toString());
        cardNew.setIdUser(enrollUser.getIdUser());
        cardNew.setIdBin(catBines.getIdBin());
        cardNew.setSeed(catBines.getBin()+idClient+dataEnroll.get("pan").toString());
        cardNew.setDateUpdate(dateStamp);
        cardNew.setDateCreated(dateStamp);

        List<CatConfigEntity> listaConfiguracion=configCardRepository.findByIdBin(catBines.getIdBin());
        for (CatConfigEntity itemcat:listaConfiguracion) {
            if(itemcat.getName().equals("pan_status")){cardNew.setStatusPan(itemcat.getValue().equals("1"));}
            if(itemcat.getName().equals("atm_status")){cardNew.setActiveAtm(itemcat.getValue().equals("1"));}
            if(itemcat.getName().equals("pos_status")){cardNew.setActivePos(itemcat.getValue().equals("1"));}
            if(itemcat.getName().equals("ecomerce_status")){cardNew.setActiveEcomerce(itemcat.getValue().equals("1"));}
            if(itemcat.getName().equals("telephone_status")){cardNew.setActiveTelephone(itemcat.getValue().equals("1"));}
            if(itemcat.getName().equals("international_status")){cardNew.setActiveInternational(Boolean.parseBoolean(itemcat.getValue()));}
            if(itemcat.getName().equals("atm_limitamountOperation")){cardNew.setLimitamountOperationAtm(BigDecimal.valueOf(Long.parseLong(itemcat.getValue())));}
            if(itemcat.getName().equals("pos_limitamountOperation")){cardNew.setLimitamountOperationPos(BigDecimal.valueOf(Long.parseLong(itemcat.getValue())));}
            if(itemcat.getName().equals("ecomerce_limitamountOperation")){cardNew.setLimitamountOperationEcomerce(BigDecimal.valueOf(Long.parseLong(itemcat.getValue())));}
            if(itemcat.getName().equals("telephone_limitamountOperation")){cardNew.setLimitamountOperationTelephone(BigDecimal.valueOf(Long.parseLong(itemcat.getValue())));}
            if(itemcat.getName().equals("international_limitamountOperation")){cardNew.setLimitamountOperationInternational(BigDecimal.valueOf(Long.parseLong(itemcat.getValue())));}
            if(itemcat.getName().equals("atm_limitamountDaily")){cardNew.setLimitamountDailyAtm(BigDecimal.valueOf(Long.parseLong(itemcat.getValue())));}
            if(itemcat.getName().equals("pos_limitamountDaily")){cardNew.setLimitamountDailyPos(BigDecimal.valueOf(Long.parseLong(itemcat.getValue())));}
            if(itemcat.getName().equals("ecomerce_limitamountDaily")){cardNew.setLimitamountDailyEcomerce(BigDecimal.valueOf(Long.parseLong(itemcat.getValue())));}
            if(itemcat.getName().equals("telephone_limitamountDaily")){cardNew.setLimitamountDailyTelephone(BigDecimal.valueOf(Long.parseLong(itemcat.getValue())));}
            if(itemcat.getName().equals("international_limitamountDaily")){cardNew.setLimitamountDailyInternational(BigDecimal.valueOf(Long.parseLong(itemcat.getValue())));}
            if(itemcat.getName().equals("atm_limitTransactionDaily")){cardNew.setLimitTransactionDailyAtm(Short.parseShort(itemcat.getValue()));}
            if(itemcat.getName().equals("pos_limitTransactionDaily")){cardNew.setLimitTransactionDailyPos(Short.parseShort(itemcat.getValue()));}
            if(itemcat.getName().equals("ecomerce_limitTransactionDaily")){cardNew.setLimitTransactionDailyEcomerce(Short.parseShort(itemcat.getValue()));}
            if(itemcat.getName().equals("telephone_limitTransactionDaily")){cardNew.setLimitTransactionDailyTelephone(Short.parseShort(itemcat.getValue()));}
            if(itemcat.getName().equals("international_limitTransactionDaily")){cardNew.setLimitTransactionDailyInternational(Short.parseShort(itemcat.getValue()));}
        }
        Map cardResponse =new HashMap();
            cardsRepository.save(cardNew);

            cardResponse.put("bin", catBines.getBin());
            cardResponse.put("pan",cardNew.getPan());
            cardResponse.put("maskpan",cardNew.getMaskcard());

        rsponse.put("message", "CREATED");
        rsponse.put("status", "OK");
        rsponse.put("card", cardResponse);
        return rsponse;
    }

    public Map enrollUser(String userId, String traceid, Map userData)
    {
        TblEnrollmentUsersEntity userRollment = new TblEnrollmentUsersEntity();
        if (isEmpty(userId)) {
            throw new RequestConflictException("clientid is null",ConstantsRest.POST_ENROLL_CONFLICT);
        }
        if (isEmpty(traceid)) {
        throw new RequestConflictException("traceid is null",ConstantsRest.POST_ENROLL_CONFLICT);
    }
            List<String> listParamsValid = new ArrayList<>();
            listParamsValid.add("usermask");
          /*  if (isNotValidParams(userData,listParamsValid)) {
                throw new RequestConflictException("Missing fields to the body of the request: " + keysNotFounds,ConstantsRest.POST_ENROLL_CONFLICT);
            }*/
            TblEnrollmentUsersEntity enrolusersEntityEntity = tblEnrolementsUsersRepository.findByIssuerclientId(userId);
            if (userDoesExist(enrolusersEntityEntity)) {
                throw new ResourceNotFoundException("This user: " + userId + " was already enrolled",ConstantsRest.POST_ENROLL_CONFLICT);
            }
            Date dateChage = new Timestamp(System.currentTimeMillis());
            java.sql.Date sqlDate = new java.sql.Date(dateChage.getTime());
            LocalTime now = LocalTime.now();
            Time time = Time.valueOf( now );

            userRollment.setIssuerclientId(userId);
            userRollment.setUsername(userData.get("usermask").toString());
            userRollment.setUserNameMask(userData.get("usermask").toString());
            userRollment.setBirthdate(sqlDate);
            userRollment.setStatus(1);
            userRollment.setSysdate(sqlDate);
            userRollment.setSystime(time);

        Map user = new HashMap();
        user.put("usermask",userRollment.getUserNameMask());
        switch (userRollment.getStatus()){
            case 1: user.put("status","1:ACTIVE");
                break;
            case 2: user.put("status","2:BLOCKED");
                break;
            case 3: user.put("status","3:DISABLE");
                break;
        }
            tblEnrolementsUsersRepository.save(userRollment);

            rsponse.put("message",  "CREATED");
            rsponse.put("status", "OK");
            rsponse.put("status", user);
            return rsponse;
    }

    public Map getInformationUser(String idclient, String traceid)
    {
       TblEnrollmentUsersEntity enrollmentUsersEntity = validationsService.validateUserExists(idclient,ConstantsRest.GET_USER_NOT_FOUND);
        Map user = new HashMap();
        switch (enrollmentUsersEntity.getStatus()){
            case 1:
                user.put("status","1:ACTIVE");
                break;
            case 2:
                user.put("status","2:BLOCKED");
                break;
            case 3:
                user.put("status","3:DISABLE");
                break;
        }
        user.put("usermask",enrollmentUsersEntity.getUserNameMask());

        Map resp = new HashMap();

        resp.put("message", "SUCCESS");
        resp.put("status", "OK");
        resp.put("user", user);
        return resp;

    }

    public Map getInformationCard(String idpan,String idclient, String traceid)
    {
        Map resp = new HashMap();

        TblEnrollmentUsersEntity enrolusersEntityEntity = tblEnrolementsUsersRepository.findByIssuerclientId(idclient);
        if (resourceDoesNotExist(enrolusersEntityEntity)) {
            throw new ResourceNotFoundException("This user: " + idclient + " does't exits",ConstantsRest.POST_ENROLL_CONFLICT);
        }
        TblCardsEntity  card = tblCardsRepository.findByPan(idpan);
        Map cardInfo = new HashMap();
        cardInfo.put("idPan",card.getPan());
        cardInfo.put("maskPan",card.getMaskcard());
        cardInfo.put("maskclient",enrolusersEntityEntity.getUserNameMask());
        cardInfo.put("status",card.isStatusPan());

        resp.put("message", "SUCCESS");
        resp.put("status", "OK");
        resp.put("card", cardInfo);
        return resp;

    }
    public Map getInformationAllCards(String idclient, String traceid)
    {
        if (isEmpty(traceid)) {
            throw new RequestConflictException("traceid is null",ConstantsRest.POST_ENROLL_CONFLICT);
        }
        Map resp = new HashMap();
        List<Map> cardsOfUser = new ArrayList<Map>();
        TblEnrollmentUsersEntity enrolusersEntityEntity = validationsService.validateUserExists(idclient,ConstantsRest.POST_ENROLL_CONFLICT);
        try {
           List<TblCardsEntity>  cardArray = getAllCards(idclient,ConstantsRest.POST_ENROLL_CONFLICT);
            for (TblCardsEntity card:cardArray ) {
                Map cardInfo = new HashMap();
                cardInfo.put("idPan",card.getPan());
                cardInfo.put("maskPan",card.getMaskcard());
                cardInfo.put("maskclient",enrolusersEntityEntity.getUserNameMask());
                cardsOfUser.add(cardInfo);
            }
            resp.put("message", "SUCCESS");
            resp.put("status", "OK");
            resp.put("cards", cardsOfUser);
            return resp;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            GenericErrorMobileResponse errorRespons = new GenericErrorMobileResponse();
            errorRespons.setMessage(ex.getMessage());
            return errorRespons.getResponse();
        }
    }

    private boolean isNotValidParamsRequest(String userId, String panCard)
    {

        String msgError = "";
        boolean clientEmpty = false;
        boolean panEmpty = false;

        if (userId.equals("") || userId.isEmpty()) {
            msgError = "Missing request header 'userId': can not be null or empty. ";
            clientEmpty = true;
        }
        if (panCard.equals("") || panCard.isEmpty()) {
            msgError += "Field idpan can not be null or empty";
            panEmpty = true;
        }
        if (clientEmpty || panEmpty) {
            log.debug("PARAM INVALIDS ::: " + msgError);
            fielsEmpty = msgError;
            return true;
        }
        return false;

    }

    private boolean isNotValidParams(Map<String, String> dataEnrol,List<String> keys)
    {
        List<String> keysNotFound = new ArrayList<>();
        for (String key : keys) {
            if(!dataEnrol.containsKey(key))
                keysNotFound.add(key);
        }
        if (keysNotFound.size() > 0) {
            log.debug("<--------------------------------------------------");
            log.debug("Missing fields to the body of the request: ");
            for (String key : keysNotFound) {
                log.debug("Key not found _:" + key);
                keysNotFounds += "[" + key + "] ";
            }
            log.debug("<--------------------------------------------------");
            return false;
        }
        return true;
    }

    /**
     * asegurarse que no viene vasio
     * @param idClient
     * @return
     */
    private  boolean isEmpty(String idClient){
        boolean clientEmpty = false;
        if (idClient.equals("") || idClient.isEmpty()) {
            fielsEmpty = "Missing request header 'userId': can not be null or empty. ";
            clientEmpty = true;
        }
        return  clientEmpty;
    }
    private  boolean isEmptyCard(String pan){
        boolean clientEmpty = false;
        if (pan.equals("") || pan.isEmpty()) {
            fielsEmpty = "Missing request 'pan': can not be null or empty. ";
            clientEmpty = true;
        }
        return  clientEmpty;
    }

    List<TblCardsEntity> getAllCards(String idClient,String error){
        Map resp = new HashMap();
        List<Map> cardsOfUser = new ArrayList<Map>();
        TblEnrollmentUsersEntity enrolusersEntityEntity = validationsService.validateUserExists(idClient,error);
         return   tblCardsRepository.findAllByIdUser(enrolusersEntityEntity.getIdUser());

    }
    void blockCardM(TblCardsEntity card){
        Timestamp dateChage = new Timestamp(System.currentTimeMillis());
        card.setStatusPan(false);
        card.setDateUpdate(dateChage);
        tblCardsRepository.save(card);
    }
    private boolean CardDoesExist(TblCardsEntity cardsEntity) {
        return cardsEntity != null;
    }
    private boolean userDoesExist(TblEnrollmentUsersEntity tblEnrollmentUsersEntity) {
        return tblEnrollmentUsersEntity != null;
    }
    private boolean resourceDoesExist(TblCardsEntity cardsEntity) {
        return cardsEntity != null;
    }
    private boolean resourceDoesNotExist(TblEnrollmentUsersEntity usersEntity) {
        return usersEntity == null;
    }
    private boolean catBinDoesNotExist(CatBinesEntity catbin) {
        return catbin == null;
    }
    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
}
