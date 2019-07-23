package com.aiti.preauthorizer.services.impl;

import com.aiti.hsm.dto.m0.M0Command;
import com.aiti.hsm.dto.m0.M1Response;
import com.aiti.hsm.exception.CryptosecBankingException;
import com.aiti.hsm.service.CryptosecBankingService;
import com.aiti.preauthorizer.services.CryptoSecurityService;
import com.aiti.preauthorizer.utils.GeneralUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Servicio encargado de obtener y cifrar
 *        una semilla
 *
 * @author Angel Contreras Torres
 * @since  18-01-2017
 */
@Service
public class CryptoSecurityServiceImpl implements CryptoSecurityService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public String getNewCryptoSeed( String user, String pan ) throws CryptosecBankingException{
        String random = UUID.randomUUID().toString().replace("-","");
        String time   = GeneralUtils.getTime("HHmmssSS");

        StringBuffer buffer = new StringBuffer();
        buffer.append( random );
        buffer.append( pan );
        buffer.append( user );
        buffer.append( time );

        /** Hasheando la semilla **/
        ShaPasswordEncoder shaEconder = new ShaPasswordEncoder(512);
        String seed = shaEconder.encodePassword(buffer.toString(), user);


        /** Cifrando comando en el HSM **/
        //String auxSeed = seed.substring(0,16);


        /** Se pone if para prueba sin HSM **/
        if( true ){
            String auxSeed = seed.substring(0,32);
            log.debug( "Seending to hsm seed: " + auxSeed + " ::  Length: " + auxSeed.length() );
            return auxSeed;
        }

        CryptosecBankingService hsmSocketService = new CryptosecBankingService(true, false);
        //log.debug( "Seending to hsm seed: " + auxSeed + " ::  Length: " + auxSeed.length() );

        M0Command m0Command = new M0Command( seed.substring(0,16) );
        M1Response response = (M1Response) hsmSocketService.execute(m0Command);

        if( response.getErrorCode() == 0 ){
            return response.getEncryptedMessage();
        }else{
            throw new CryptosecBankingException("HSM returned an invalid code. [ "+response.getErrorCode()+"]");
        }
    }
}
