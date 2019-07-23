package com.aiti.preauthorizer.controller;

import com.aiti.preauthorizer.dto.forms.ChangePassForm;
import com.aiti.preauthorizer.services.AdminCodesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Controlador que se encarga de administrar
 *       los códigos de enrolamiento
 *
 * @author Angel Contreras Torres
 * @since  11-10-2016
 */

@Controller
@RequestMapping("/codes")
public class AdminCodesController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdminCodesService adminCodesService;

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    @ResponseBody
    public String validateUser( HttpServletResponse response ){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String maskName = "OK";

        if( authentication != null ){
            User user = (User) authentication.getPrincipal();

            maskName = adminCodesService.isNewAccount( user.getUsername() );

            if (  maskName!= null ){
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE );
            }

        }else{
            response.setStatus(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED);
        }

        return maskName;
    }


    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    @ResponseBody
    public String activateCode(@Valid @ModelAttribute() ChangePassForm form, Errors errors, HttpServletResponse response){

        log.info( form.toString() );

        if (errors.hasErrors()) {
            log.warn("Los parametros de entrada son incorrectos :: " + form.toString());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "NO_OK";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if( authentication != null ){
            User user = (User) authentication.getPrincipal();

            int respCode = adminCodesService.changePin( user.getUsername(), form );

            if (respCode > 0){
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        }else {
            response.setStatus(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED);
        }

        return "OK";
    }
}
