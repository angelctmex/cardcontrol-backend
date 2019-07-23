package com.aiti.preauthorizer.services;

import com.aiti.preauthorizer.domain.admin.User;
import com.aiti.preauthorizer.dto.forms.ChangePassForm;
import com.aiti.preauthorizer.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio que se encarga de administrar
 *       los códigos de enrolamiento
 *
 * @author Angel Contreras Torres
 * @since  11-10-2016
 */

@Service
public class AdminCodesService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StandardPasswordEncoder standardPasswordEncoder;

    @Transactional
    public String isNewAccount( String username ){
        String maskName = null;

        User user = userRepository.findByUsernameCaseInsensitive(username);

        if( user != null ) { // && user.getResetPasswordKey()!=null && user.getResetPasswordKey().equalsIgnoreCase( "new" )  ){
            // Collection enrolementUsers  = user.getTblEnrolementsUsersesByUsername();

            /*if( enrolementUsers != null && enrolementUsers.size() > 0  ){
                TblEnrollmentUsersEntity usersEntity = (TblEnrollmentUsersEntity)enrolementUsers.toArray()[0];
                maskName = usersEntity.getUserNameMask();
            }*/
            maskName = user.getUsername();
        }
        return maskName;
    }

    @Transactional
    public int changePin(String username, ChangePassForm codeActivationForm){

        int respCode = -1;

        /** Validando que los pines coincidan **/
        if( codeActivationForm.getPin().equals( codeActivationForm.getValidatePin() ) ){
            User user = userRepository.findByUsernameCaseInsensitive(username);

            try{
                String hashPassword = standardPasswordEncoder.encode( codeActivationForm.getPin() );

                user.setPassword( hashPassword );
                // user.setResetPasswordKey( "active" );

                userRepository.save(user);

                respCode = 0;
            }catch (Exception e){
                e.printStackTrace();
                respCode = 2; //No fue posible actualizar el usuario
            }

        }else{
            respCode = 1; //Los pines capturados no existen
        }

        return respCode;
    }


}
