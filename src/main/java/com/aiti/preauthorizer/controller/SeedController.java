package com.aiti.preauthorizer.controller;

import com.aiti.preauthorizer.dto.TokenMobileResponse;
import com.aiti.preauthorizer.services.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador que se encarga de regenerar una semilla
 *
 * @author Angel Contreras Torres
 * @since 26-09-2016
 */

@Controller
@RequestMapping("/seed/")
public class SeedController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/{panid}/", method = RequestMethod.GET)
    @ResponseBody
    public TokenMobileResponse seed(@PathVariable String panid) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // return tokenService.tokenSynchronization(authentication);
        return null;
    }

    // ## CVV dinamico ##
    // - [ ] synchronizationCvv [POST]

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map synchronizationCvv(@RequestBody Map body , HttpServletResponse response) {
        log.debug("-------------------------------------------------->");
        log.debug("GET ::: seeds");
        log.debug("PAN Recivido : "+ body.toString());
        log.debug("<-------------------------------------------------");

        Map respons = new HashMap<>();
        respons.put("status","OK");
        respons.put("seed","k01g2qa3pomfp321ij03kfp3ihslmqlcq");
        respons.put("ccvtimelife",20);
     //   {
     //       "status": "OK",
     //           "seed": "k01g2qa3pomfp321ij03kfp3ihslmqlcq",
     //           "ccvtimelife": 20
     //   }
        return respons;
    }

}
