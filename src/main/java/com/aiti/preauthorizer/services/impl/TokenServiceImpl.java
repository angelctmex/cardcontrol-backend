package com.aiti.preauthorizer.services.impl;

import com.aiti.hsm.exception.CryptosecBankingException;
import com.aiti.preauthorizer.domain.app.CatBinesEntity;
import com.aiti.preauthorizer.domain.app.TblCardsEntity;
import com.aiti.preauthorizer.domain.app.TblEnrollmentUsersEntity;
import com.aiti.preauthorizer.dto.exceptions.RequestBadException;
import com.aiti.preauthorizer.dto.exceptions.ResourceNotFoundException;
import com.aiti.preauthorizer.dto.exceptions.TokenMobileResponse;
import com.aiti.preauthorizer.repository.CardsRepository;
import com.aiti.preauthorizer.repository.CatBinesRepository;
import com.aiti.preauthorizer.repository.EnrollmentsUsersRepository;
import com.aiti.preauthorizer.services.CryptoSecurityService;
import com.aiti.preauthorizer.services.TokenService;
import com.aiti.preauthorizer.services.validations.ValidationsService;
import com.aiti.preauthorizer.utils.ConstantsRest;
// import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zeus on 1/18/17.
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    EnrollmentsUsersRepository enrolementsUsersRepository;

    @Autowired
    CardsRepository cardsRepository;

    @Autowired
    CatBinesRepository catBinesRepository;

    @Autowired
    CryptoSecurityService cryptoSecurityService;

    @Autowired
    ValidationsService validationsService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final AtomicLong counter = new AtomicLong();

    @Transactional
    public TokenMobileResponse tokenSynchronization(Authentication authentication, String userId, String panId, String traceId) {
        TokenMobileResponse response = new TokenMobileResponse();

        validationsService.validParamsRequestCard(userId, panId, traceId, ConstantsRest.GET_SEED_BAD_REQUEST);
        TblCardsEntity card = validationsService.validateUserAndCardExists(userId, panId, ConstantsRest.GET_SEED_NOT_FOUND);

        TblEnrollmentUsersEntity enrollmentUsersEntity = enrolementsUsersRepository.findByIdUser( card.getIdUser() );

        log.debug("TraceId _: " + traceId);
        log.info("\n");
        log.info("------------------------------------------------------------------------------------->>");
        try {

            /** Obteniendo los datos del usuario autenticado **/
            User user = (User) authentication.getPrincipal();
            // log.info( ReflectionToStringBuilder.toString(user) );

            /** Obteniendo los datos de la autenticación OAuth **/
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
            // log.info(ReflectionToStringBuilder.toString(oAuth2AuthenticationDetails));

            /** Obteniendo la tarjeta **/
            //TODO Realizar bien la obtención de la tarjeta para evitar null pointers
            String encryptedToken = cryptoSecurityService.getNewCryptoSeed( card.getPan(), enrollmentUsersEntity.getUsername() );

            /** Obteniendo tiempo de vida cvv **/
            int cvvTimeLife = getCvvTimeLife(card);

            /** Generando respuesta **/
            response = new TokenMobileResponse(encryptedToken, cvvTimeLife);

            /** Almacenando la semilla **/
            card.setSeed( response.getSeed() );
            cardsRepository.save( card );

            // log.info(ReflectionToStringBuilder.toString(response));
            // log.info(ReflectionToStringBuilder.toString(card));

            log.info("<<-------------------------------------------------------------------------------------\n");

        }catch(CryptosecBankingException cbe){
            cbe.printStackTrace();
        }

        return response;
    }

    private Integer getCvvTimeLife (TblCardsEntity cardEntity) {
        CatBinesEntity catBines = catBinesRepository.findByIdBin(cardEntity.getIdBin());
        return catBines.getTimelifeCvv();
    }

}
