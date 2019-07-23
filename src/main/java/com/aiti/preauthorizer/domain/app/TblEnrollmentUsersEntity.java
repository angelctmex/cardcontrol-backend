package com.aiti.preauthorizer.domain.app;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

/**
 * Created by zeus on 9/30/16.
 */
@Entity
@Table(name = "tbl_enrollment_users", schema = "public", catalog = "preauthjava")
public class TblEnrollmentUsersEntity {
    private long idUser;
    private String issuerclientId;
    private String username;
    private String userNameMask;
    private Date birthdate;
    private int status;
    private Date sysdate;
    private Time systime;
    private Collection<TblCardsEntity> tblCardsByIdUser;

    @Id
    @Column(name = "id_user")
    @SequenceGenerator(name="pk_sequence",sequenceName="enrollment_users_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_sequence")
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "issuerclient_id")
    public String getIssuerclientId() {
        return issuerclientId;
    }

    public void setIssuerclientId(String issuerclientId) {
        this.issuerclientId = issuerclientId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = -1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "user_name_mask", nullable = true, length = -1)
    public String getUserNameMask() {
        return userNameMask;
    }

    public void setUserNameMask(String userNameMask) {
        this.userNameMask = userNameMask;
    }

    @Basic
    @Column(name = "birthdate", nullable = true)
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "sysdate", nullable = false)
    public Date getSysdate() {
        return sysdate;
    }

    public void setSysdate(Date sysdate) {
        this.sysdate = sysdate;
    }

    @Basic
    @Column(name = "systime", nullable = false)
    public Time getSystime() {
        return systime;
    }

    public void setSystime(Time systime) {
        this.systime = systime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblEnrollmentUsersEntity that = (TblEnrollmentUsersEntity) o;

        if (status != that.status) return false;
        if (idUser != that.status) return false;
        if (issuerclientId != null ? !issuerclientId.equals(that.issuerclientId) : that.issuerclientId != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (userNameMask != null ? !userNameMask.equals(that.userNameMask) : that.userNameMask != null) return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;
        if (sysdate != null ? !sysdate.equals(that.sysdate) : that.sysdate != null) return false;
        if (systime != null ? !systime.equals(that.systime) : that.systime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idUser ^ (idUser >>> 32));
        result = 31 * result + (issuerclientId != null ? issuerclientId.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (userNameMask != null ? userNameMask.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (sysdate != null ? sysdate.hashCode() : 0);
        result = 31 * result + (systime != null ? systime.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "tblEnrollmentUsersByIdUser", cascade = {CascadeType.PERSIST})
    public Collection<TblCardsEntity> getTblCardsByIdUser() { return tblCardsByIdUser; }

    public void setTblCardsByIdUser(Collection<TblCardsEntity> tblCardsByIdUser) { this.tblCardsByIdUser = tblCardsByIdUser; }
}
