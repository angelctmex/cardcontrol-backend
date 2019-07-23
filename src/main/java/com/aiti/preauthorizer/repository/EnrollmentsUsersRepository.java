package com.aiti.preauthorizer.repository;

import com.aiti.preauthorizer.domain.app.TblEnrollmentUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zeus on 10/6/16.
 */
public interface EnrollmentsUsersRepository extends JpaRepository<TblEnrollmentUsersEntity, String> {

    TblEnrollmentUsersEntity findByUsername(String username);
    TblEnrollmentUsersEntity findByIdUser(long idUser);
    TblEnrollmentUsersEntity findByIssuerclientId(String issuerclientId);

}
