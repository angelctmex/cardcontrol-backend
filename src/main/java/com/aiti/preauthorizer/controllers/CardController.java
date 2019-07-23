package com.aiti.preauthorizer.controller;


import com.aiti.preauthorizer.services.postgres.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
/**
 * Controlador que se encarga de administrar las tarjetas en el sistema
 *
 * @author Angel Contreras Torres
 * @since 16-05-2019
 */
@Controller
@RequestMapping("/user")
public class CardController {

        @Autowired
        CardService cardserv;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value = "/{iduser}/cards/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getCards(@PathVariable(value = "iduser") String iduser,
                                   @RequestHeader(required = false) String traceid,
                                   HttpServletResponse response) {
        log.debug( "<-----------------Obteniendo datos de las tarjetas:" + iduser +"----------------->");
        log.debug( "<-----------------traiceid:" + traceid +"----------------->");
        return ResponseEntity.status(HttpStatus.OK).body( cardserv.getInformationAllCards(iduser,traceid));
    }

    // obtener informacion de targeta
    @RequestMapping(value = "/card/{idpan}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getCard(@PathVariable String idpan,
                        @RequestHeader(required = false) String userId,
                          @RequestHeader(required = false) String traceid) {

        log.debug( "<-----------------Obteniendo datos de la tarjeta:" + idpan +"----------------->");
        return ResponseEntity.status(HttpStatus.OK).body( null);

    }

    // ## blockear tarjeta ##
    @RequestMapping(value = "/card/{idpan}/status/{idstatus}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity cardLock(@PathVariable String idpan, @PathVariable int idstatus,
                        @RequestHeader(required = false) String userId,
                        @RequestHeader(required = false) String traceid,
                        @RequestBody(required = false) String body, HttpServletResponse response) {
        log.debug( "<-----------------cambio de estatus de la tarjeta:" + idpan +"----------------->");
        return ResponseEntity.status(HttpStatus.OK).body( cardserv.blockCard(idpan,userId,traceid,idstatus));
    }

    // ## blockear usuario ##
    @RequestMapping(value = "/{iduser}/status/{idstatus}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity userLock(@PathVariable String iduser, @PathVariable String idstatus,
                        @RequestHeader(required = false) String traceid) {
        log.debug( "<----------------- Cambio de estatus de la tarjeta:" + iduser +"----------------->");
        return ResponseEntity.status(HttpStatus.OK).body( cardserv.blockUser(iduser,traceid,idstatus));
    }

    // ## servicio para enrolar USUARIO##
    @RequestMapping(value = "/{iduser}/enrollment/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity newEnrollmentUser(@PathVariable(value = "iduser") String iduser,
                                 @RequestHeader(required = false) String traceid,
                                 @RequestBody(required = false) Map body,
                                 HttpServletResponse response) {
        log.debug( "<----------------- Enrolamiento de usuario:" + iduser + "----------------->");
        return ResponseEntity.status(HttpStatus.CREATED).body( cardserv.enrollUser(iduser,traceid,body));
    }
    // - [ ] newEnrollmentCard[POST]
    @RequestMapping(value = "/{clientid}/enrollment/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUserInfo(@PathVariable String clientid,
                                            @RequestHeader(required = false) String traceid,
                                            HttpServletResponse response) {
        log.debug( "<----------------- get info User:" + clientid + "----------------->");
        return ResponseEntity.status(HttpStatus.CREATED).body(cardserv.getInformationUser(clientid,traceid));
    }
    // - [ ] newEnrollmentCard[POST]
    @RequestMapping(value = "/{clientid}/cards/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity newEnrollmentCard(@PathVariable String clientid,
                                 @RequestHeader(required = false) String traceid,
                                 @RequestBody(required = false) Map<String,Map<String,String>> body,
                                 HttpServletResponse response) {
        log.debug( "<----------------- Enrolamiento de tarjeta:" + clientid + "----------------->");

        return ResponseEntity.status(HttpStatus.OK).body( cardserv.enrollCard(clientid,traceid,body.get("card")));
    }
}
