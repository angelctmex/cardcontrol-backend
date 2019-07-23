package com.aiti.preauthorizer.services.postgres.impl;

import com.aiti.preauthorizer.domain.app.TblCardsEntity;
import com.aiti.preauthorizer.repository.TblCardsRepository;
import com.aiti.preauthorizer.services.postgres.MobileResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class MobileResponseServiceImpl implements MobileResponseService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TblCardsRepository cardsRepository;

    public Map<String, Object> generateSuccessResponseGet (TblCardsEntity cardEntity, String typeChannel, String traceId) {

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> amounts = new HashMap<>();

        switch (typeChannel) {
            case "amounts":
                amounts.put("atmAmount", cardEntity.getLimitamountDailyAtm());
                amounts.put("posAmount", cardEntity.getLimitamountDailyPos());
                amounts.put("ecomerceAmount", cardEntity.getLimitamountDailyEcomerce());
                amounts.put("telephoneAmount", cardEntity.getLimitamountDailyTelephone());
                amounts.put("internationAmount", cardEntity.getLimitamountDailyInternational());
                response.put("amounts", amounts);
                printLogMovementsAndAmounts(amounts, "Obteniendo Limites de compra : ", traceId);
                break;
            case  "channels":
                Map<String, Object> channels = new HashMap<>();
                channels.put("atm", cardEntity.getLimitTransactionDailyAtm());
                channels.put("stores", cardEntity.getLimitTransactionDailyPos());
                channels.put("internet", cardEntity.getLimitTransactionDailyEcomerce());
                channels.put("phone", cardEntity.getLimitTransactionDailyTelephone());
                channels.put("international", cardEntity.getLimitTransactionDailyInternational());
                response.put("channels", channels);
                printLogMovementsAndAmounts(channels, "Obteniendo No. de movimientos por dia: ", traceId);
                break;
            case "operation":
                amounts.put("atmAmount", cardEntity.getLimitamountOperationAtm());
                amounts.put("posAmount", cardEntity.getLimitamountOperationPos());
                amounts.put("ecomerceAmount", cardEntity.getLimitamountOperationEcomerce());
                amounts.put("telephoneAmount", cardEntity.getLimitamountOperationTelephone());
                amounts.put("internationAmount", cardEntity.getLimitamountOperationInternational());
                response.put("amounts", amounts);
                printLogMovementsAndAmounts(amounts, "Obteniendo Limites de compra por operacion: ", traceId);
                break;
            case  "status":
                Map<String, Object> Status = new HashMap<>();
                Status.put("status_pan", cardEntity.isStatusPan()?1:0);
                Status.put("atm", cardEntity.isActiveAtm()?1:0);
                Status.put("pos", cardEntity.isActivePos()?1:0);
                Status.put("ecomerce", cardEntity.isActiveEcomerce()?1:0);
                Status.put("phone", cardEntity.isActiveTelephone()?1:0);
                Status.put("international", cardEntity.isActiveInternational()?1:0);
                response.put("channels", Status);
                printLogMovementsAndAmounts(Status, "Obteniendo Status de canales: ", traceId);
                break;
        }

        response.put("status", HttpStatus.OK.getReasonPhrase());
        response.put("message", "SUCCESS");

        return response;
    }

    public Map<String, Object> generateSuccessResponseSetting (TblCardsEntity cardEntity, String typeChannel, Map<String, Object> object, String traceId) {

        Map<String, Object> response = new HashMap<>();
        Timestamp updateDate = new Timestamp(System.currentTimeMillis());

        switch (typeChannel) {
            case "amounts":

                cardEntity.setLimitamountDailyAtm(new BigDecimal(object.get("atmAmount").toString()));
                cardEntity.setLimitamountDailyPos(new BigDecimal(object.get("posAmount").toString()));
                cardEntity.setLimitamountDailyEcomerce(new BigDecimal(object.get("ecomerceAmount").toString()));
                cardEntity.setLimitamountDailyTelephone(new BigDecimal(object.get("telephoneAmount").toString()));
                cardEntity.setLimitamountDailyInternational(new BigDecimal(object.get("internationAmount").toString()));
                cardEntity.setDateUpdate(updateDate);
                cardsRepository.save(cardEntity);
                printLogMovementsAndAmounts(object, "Modificando Limites de compra:", traceId);
                response.put("amounts", object);
                response.put("message", "Los montos fueron actualizados de manera correcta");

                break;
            case "channels":

                cardEntity.setLimitTransactionDailyAtm(new Short(object.get("atm").toString()));
                cardEntity.setLimitTransactionDailyPos(new Short(object.get("pos").toString()));
                cardEntity.setLimitTransactionDailyEcomerce(new Short(object.get("ecomerce").toString()));
                cardEntity.setLimitTransactionDailyTelephone(new Short(object.get("telephone").toString()));
                cardEntity.setLimitTransactionDailyInternational(new Short(object.get("international").toString()));
                cardEntity.setDateUpdate(updateDate);
                cardsRepository.save(cardEntity);
                printLogMovementsAndAmounts(object, "Modificando No. de movimientos por dia: ", traceId);
                response.put("channels", object);
                response.put("message", "Los montos fueron actualizados de manera correcta");
                break;
            case "operation":

                cardEntity.setLimitamountOperationAtm(new BigDecimal(object.get("atmAmount").toString()));
                cardEntity.setLimitamountOperationPos(new BigDecimal(object.get("posAmount").toString()));
                cardEntity.setLimitamountOperationEcomerce(new BigDecimal(object.get("ecomerceAmount").toString()));
                cardEntity.setLimitamountOperationTelephone(new BigDecimal(object.get("telephoneAmount").toString()));
                cardEntity.setLimitamountOperationInternational(new BigDecimal(object.get("internationAmount").toString()));
                cardEntity.setDateUpdate(updateDate);
                cardsRepository.save(cardEntity);
                printLogMovementsAndAmounts(object, "Modificando Limites de compra por operacion: ", traceId);
                response.put("amounts", object);
                response.put("message", "Los montos fueron actualizados de manera correcta");
                break;
            case  "status":
                cardEntity.setActiveAtm(object.get("atm").toString().equals("1"));
                cardEntity.setActivePos(object.get("pos").toString().equals("1"));
                cardEntity.setActiveEcomerce(object.get("ecomerce").toString().equals("1"));
                cardEntity.setActiveTelephone(object.get("phone").toString().equals("1"));
                cardEntity.setActiveInternational(object.get("international").equals("1"));
                cardEntity.setStatusPan(object.get("status_pan").toString().equals("1"));
                cardEntity.setDateUpdate(updateDate);
                cardsRepository.save(cardEntity);
                response.put("channels", object);
                response.put("message", "CREATED");
                printLogMovementsAndAmounts(object, "Modificando Status de canales: ", traceId);
                break;
        }

        response.put("status", HttpStatus.OK.getReasonPhrase());

        return response;
    }

    private void printLogMovementsAndAmounts(Map<String, Object> object, String logInfo, String traceId) {
        log.debug("-------------------------------------------------->");
        log.debug(logInfo + " TraceId _:" + traceId);
        object.forEach((k, v) -> log.debug(
                k.equals("atmAmount") || k.equals("atm") || k.equals("operationAtmAmount") ?
                        "Monto Máximo para " + k.replace("Amount", " ") + "     : " + v
                        : "Monto Máximo compras " + k.replace("Amount", " ") + "     :" + v));
        log.debug("<--------------------------------------------------");
    }
}
