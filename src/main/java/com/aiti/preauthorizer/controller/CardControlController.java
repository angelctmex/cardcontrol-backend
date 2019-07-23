package com.aiti.preauthorizer.controller;

import com.aiti.preauthorizer.dto.exceptions.TokenMobileResponse;
import com.aiti.preauthorizer.services.TokenService;
import com.aiti.preauthorizer.services.postgres.CardControlService;
import com.aiti.preauthorizer.utils.ConstantsRest;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Controlador que se encarga de administrar los card controls de las tarjetas
 *
 * @author Angel Contreras Torres
 * @since 11-07-2017
 */
@Controller
@RequestMapping("/card")
public class CardControlController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TokenService tokenService;

    @Autowired
    CardControlService cardControlService;

    @RequestMapping(value="/{idpan}/amountlimits", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateAmountsLimit(@PathVariable(value = "idpan") @NotBlank String idpan,
                                              @RequestHeader(required = false) String userId,
                                              @RequestHeader(required = false) String traceid,
                                              @RequestBody(required = false) Map<String, Object> amounts) {
        return ResponseEntity.status(HttpStatus.OK).body(cardControlService.setMaximAmountsDay(userId, idpan, amounts, traceid));
    }
    
    @RequestMapping(value="/{idpan}/amountlimits", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getCurrentAmountsLimit(@PathVariable(value = "idpan") @NotBlank String idpan,
                                                 @RequestHeader(required = false) String userId,
                                                 @RequestHeader(required = false) String traceid) {
        return ResponseEntity.status(HttpStatus.OK).body(cardControlService.getLimitAmountsDay(userId, idpan, traceid));
    }

    @RequestMapping(value="/{idpan}/amountlimits/operation", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateAmountsLimitOperation(@PathVariable(value = "idpan") @NotBlank String idpan,
                                                       @RequestHeader(required = false) String userId,
                                                       @RequestHeader(required = false) String traceid,
                                                       @RequestBody(required = false) Map<String, Object> amounts) {
        return ResponseEntity.status(HttpStatus.OK).body(cardControlService.setMaximAmountsOperation(userId, idpan, amounts, traceid));
    }

    @RequestMapping(value="/{idpan}/amountlimits/operation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getCurrentAmountsLimitOperation(@PathVariable(value = "idpan") @NotBlank String idpan,
                                                          @RequestHeader(required = false) String userId,
                                                          @RequestHeader(required = false) String traceid) {
        return ResponseEntity.status(HttpStatus.OK).body(cardControlService.getLimitAmountsOperation(userId, idpan, traceid));
    }

    /**
     * getStatusChannels [GET]
     * ## Obtener estatus de canales de acceso actuales ##
     * @param idpan
     * @param response
     * @return
     */
    @RequestMapping(value = "/{idpan}/channels/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getStatusChannels(@PathVariable String idpan,
                                 @RequestHeader(required = false) String userId,
                                 @RequestHeader(required = false) String traceid,
                                 HttpServletResponse response) {
        log.debug("-------------------------------------------------->");
        log.debug("POST ::: statuschannels");
        log.debug("PAN Recivido : " + idpan.toString());
        log.debug("<-------------------------------------------------");
        return ResponseEntity.status(HttpStatus.OK).body(cardControlService.getCurrentStatusAccessChannels( idpan, traceid, userId));
    }

    /**
     * setSattusChanel
     * @param body
     * @param response
     * @return
     */
    @RequestMapping(value = "/{idpan}/channels/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity setStatusChannel(@PathVariable String idpan,
                                @RequestHeader(required = false) String userId,
                                @RequestHeader(required = false) String traceid,
                                @RequestBody(required = false) Map body, HttpServletResponse response) {
        log.debug("-------------------------------------------------->");
        log.debug("POST ::: statuschannels");
        log.debug("PAN Recivido : " + idpan.toString());
        log.debug("<-------------------------------------------------");

        return  ResponseEntity.status(HttpStatus.OK).body(cardControlService.configureCardAccessChannels( idpan, userId, traceid, body));
    }

    @RequestMapping(value = "/{idpan}/movementslimits/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getCurrentMovementLimits(@PathVariable String idpan,
                                                   @RequestHeader(required = false) String userId,
                                                   @RequestHeader(required = false) String traceid) {
        return ResponseEntity.status(HttpStatus.OK).body(cardControlService.getNumberTransactionsDay(userId, idpan, traceid));
    }

    @RequestMapping(value = "/{idpan}/movementslimits/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateMovementsLimits(@PathVariable String idpan,
                                           @RequestHeader(required = false) String userId,
                                           @RequestHeader(required = false) String traceid,
                                           @RequestBody(required = false) Map<String, Object> movements) {
    return ResponseEntity.status(HttpStatus.OK).body(cardControlService.configureNumberTransactionsDay(userId, idpan, traceid, movements));

    }

    @RequestMapping(value = "/{idpan}/seed/", method = RequestMethod.GET)
    @ResponseBody
    public TokenMobileResponse synchronizeSeed(@PathVariable String idpan,
                                               @RequestHeader(required = false) String userId,
                                               @RequestHeader(required = false) String traceid) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return tokenService.tokenSynchronization(authentication, userId, idpan, traceid);
    }
}
