package com.aiti.preauthorizer.services;

import com.aiti.hsm.exception.CryptosecBankingException;

/**
 * Servicio que se encarga de interactuar
 *       con el equipo Criptografico
 *
 * @author Angel Contreras Torres
 * @since  17-01-2017
 */

public interface CryptoSecurityService {

    public String getNewCryptoSeed( String user, String pan ) throws CryptosecBankingException;
}
