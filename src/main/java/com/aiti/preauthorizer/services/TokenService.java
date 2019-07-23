package com.aiti.preauthorizer.services;

import com.aiti.preauthorizer.dto.exceptions.TokenMobileResponse;
import org.springframework.security.core.Authentication;

/**
 * Servicio que se encarga de sincronizar
 *       el Token con el movil
 *
 * @author Angel Contreras Torres
 * @since  17-01-2017
 */
public interface TokenService {
    TokenMobileResponse tokenSynchronization(Authentication authentication, String userId, String panId, String traceId);
}
