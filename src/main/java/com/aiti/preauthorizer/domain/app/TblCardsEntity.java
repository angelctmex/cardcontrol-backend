package com.aiti.preauthorizer.domain.app;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by zeus on 9/30/16.
 */
@Entity
@Table(name = "tbl_cards", schema = "public", catalog = "preauthjava")
@IdClass(TblCardsEntityPK.class)
public class TblCardsEntity implements Serializable {
    private long idPan;
    private long idUser;
    private long idBin;
    private String pan;
    private String maskcard;
    private String seed;
    private boolean statusPan;
    private boolean isActiveAtm;
    private boolean isActivePos;
    private boolean isActiveTelephone;
    private boolean isActiveInternational;
    private boolean isActiveEcomerce;
    private BigDecimal limitamountOperationAtm;
    private BigDecimal limitamountOperationPos;
    private BigDecimal limitamountOperationTelephone;
    private BigDecimal limitamountOperationInternational;
    private BigDecimal limitamountOperationEcomerce;
    private BigDecimal limitamountDailyAtm;
    private BigDecimal limitamountDailyPos;
    private BigDecimal limitamountDailyTelephone;
    private BigDecimal limitamountDailyInternational;
    private BigDecimal limitamountDailyEcomerce;
    private short limitTransactionDailyAtm;
    private short limitTransactionDailyPos;
    private short limitTransactionDailyTelephone;
    private short limitTransactionDailyInternational;
    private short limitTransactionDailyEcomerce;
    private Timestamp dateCreated;
    private Timestamp dateUpdate;
    private TblEnrollmentUsersEntity tblEnrollmentUsersByIdUser;
    private CatBinesEntity catBinesByIdBin;

    @Id
    @Column(name = "id_pan")
    @SequenceGenerator(name="pk_sequence",sequenceName="TBL_CARDS_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_sequence")
    public long getIdPan() { return idPan; }

    public void setIdPan(long idPan) { this.idPan = idPan; }

    @Id
    @Column(name = "id_user")
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "id_bin")
    public long getIdBin() {
        return idBin;
    }

    public void setIdBin(long idBin) {
        this.idBin = idBin;
    }

    @Basic
    @Column(name = "pan")
    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    @Basic
    @Column(name = "maskcard")
    public String getMaskcard() {
        return maskcard;
    }

    public void setMaskcard(String maskcard) {
        this.maskcard = maskcard;
    }

    @Basic
    @Column(name = "seed")
    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    @Basic
    @Column(name = "status_pan")
    public boolean isStatusPan() {
        return statusPan;
    }

    public void setStatusPan(boolean statusPan) {
        this.statusPan = statusPan;
    }

    @Basic
    @Column(name = "is_active_atm")
    public boolean isActiveAtm() {
        return isActiveAtm;
    }

    public void setActiveAtm(boolean activeAtm) {
        isActiveAtm = activeAtm;
    }

    @Basic
    @Column(name = "is_active_pos")
    public boolean isActivePos() {
        return isActivePos;
    }

    public void setActivePos(boolean activePos) {
        isActivePos = activePos;
    }

    @Basic
    @Column(name = "is_active_telephone")
    public boolean isActiveTelephone() {
        return isActiveTelephone;
    }

    public void setActiveTelephone(boolean activeTelephone) {
        isActiveTelephone = activeTelephone;
    }

    @Basic
    @Column(name = "is_active_international")
    public boolean isActiveInternational() {
        return isActiveInternational;
    }

    public void setActiveInternational(boolean activeInternational) {
        isActiveInternational = activeInternational;
    }

    @Basic
    @Column(name = "is_active_ecomerce")
    public boolean isActiveEcomerce() {
        return isActiveEcomerce;
    }

    public void setActiveEcomerce(boolean activeEcomerce) {
        isActiveEcomerce = activeEcomerce;
    }

    @Basic
    @Column(name = "limitamount_operation_atm")
    public BigDecimal getLimitamountOperationAtm() {
        return limitamountOperationAtm;
    }

    public void setLimitamountOperationAtm(BigDecimal limitamountOperationAtm) {
        this.limitamountOperationAtm = limitamountOperationAtm;
    }

    @Basic
    @Column(name = "limitamount_operation_pos")
    public BigDecimal getLimitamountOperationPos() {
        return limitamountOperationPos;
    }

    public void setLimitamountOperationPos(BigDecimal limitamountOperationPos) {
        this.limitamountOperationPos = limitamountOperationPos;
    }

    @Basic
    @Column(name = "limitamount_operation_telephone")
    public BigDecimal getLimitamountOperationTelephone() {
        return limitamountOperationTelephone;
    }

    public void setLimitamountOperationTelephone(BigDecimal limitamountOperationTelephone) {
        this.limitamountOperationTelephone = limitamountOperationTelephone;
    }

    @Basic
    @Column(name = "limitamount_operation_international")
    public BigDecimal getLimitamountOperationInternational() {
        return limitamountOperationInternational;
    }

    public void setLimitamountOperationInternational(BigDecimal limitamountOperationInternational) {
        this.limitamountOperationInternational = limitamountOperationInternational;
    }

    @Basic
    @Column(name = "limitamount_operation_ecomerce")
    public BigDecimal getLimitamountOperationEcomerce() {
        return limitamountOperationEcomerce;
    }

    public void setLimitamountOperationEcomerce(BigDecimal limitamountOperationEcomerce) {
        this.limitamountOperationEcomerce = limitamountOperationEcomerce;
    }

    @Basic
    @Column(name = "limitamount_daily_atm")
    public BigDecimal getLimitamountDailyAtm() {
        return limitamountDailyAtm;
    }

    public void setLimitamountDailyAtm(BigDecimal limitamountDailyAtm) {
        this.limitamountDailyAtm = limitamountDailyAtm;
    }

    @Basic
    @Column(name = "limitamount_daily_pos")
    public BigDecimal getLimitamountDailyPos() {
        return limitamountDailyPos;
    }

    public void setLimitamountDailyPos(BigDecimal limitamountDailyPos) {
        this.limitamountDailyPos = limitamountDailyPos;
    }

    @Basic
    @Column(name = "limitamount_daily_telephone")
    public BigDecimal getLimitamountDailyTelephone() {
        return limitamountDailyTelephone;
    }

    public void setLimitamountDailyTelephone(BigDecimal limitamountDailyTelephone) {
        this.limitamountDailyTelephone = limitamountDailyTelephone;
    }

    @Basic
    @Column(name = "limitamount_daily_international")
    public BigDecimal getLimitamountDailyInternational() {
        return limitamountDailyInternational;
    }

    public void setLimitamountDailyInternational(BigDecimal limitamountDailyInternational) {
        this.limitamountDailyInternational = limitamountDailyInternational;
    }

    @Basic
    @Column(name = "limitamount_daily_ecomerce")
    public BigDecimal getLimitamountDailyEcomerce() {
        return limitamountDailyEcomerce;
    }

    public void setLimitamountDailyEcomerce(BigDecimal limitamountDailyEcomerce) {
        this.limitamountDailyEcomerce = limitamountDailyEcomerce;
    }

    @Basic
    @Column(name = "limit_transaction_daily_atm")
    public short getLimitTransactionDailyAtm() {
        return limitTransactionDailyAtm;
    }

    public void setLimitTransactionDailyAtm(short limitTransactionDailyAtm) {
        this.limitTransactionDailyAtm = limitTransactionDailyAtm;
    }

    @Basic
    @Column(name = "limit_transaction_daily_pos")
    public short getLimitTransactionDailyPos() {
        return limitTransactionDailyPos;
    }

    public void setLimitTransactionDailyPos(short limitTransactionDailyPos) {
        this.limitTransactionDailyPos = limitTransactionDailyPos;
    }

    @Basic
    @Column(name = "limit_transaction_daily_telephone")
    public short getLimitTransactionDailyTelephone() {
        return limitTransactionDailyTelephone;
    }

    public void setLimitTransactionDailyTelephone(short limitTransactionDailyTelephone) {
        this.limitTransactionDailyTelephone = limitTransactionDailyTelephone;
    }

    @Basic
    @Column(name = "limit_transaction_daily_international")
    public short getLimitTransactionDailyInternational() {
        return limitTransactionDailyInternational;
    }

    public void setLimitTransactionDailyInternational(short limitTransactionDailyInternational) {
        this.limitTransactionDailyInternational = limitTransactionDailyInternational;
    }

    @Basic
    @Column(name = "limit_transaction_daily_ecomerce")
    public short getLimitTransactionDailyEcomerce() {
        return limitTransactionDailyEcomerce;
    }

    public void setLimitTransactionDailyEcomerce(short limitTransactionDailyEcomerce) {
        this.limitTransactionDailyEcomerce = limitTransactionDailyEcomerce;
    }

    @Basic
    @Column(name = "date_created")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "date_update")
    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblCardsEntity that = (TblCardsEntity) o;
        return idPan == that.idPan &&
                idUser == that.idUser &&
                idBin == that.idBin &&
                statusPan == that.statusPan &&
                isActiveAtm == that.isActiveAtm &&
                isActivePos == that.isActivePos &&
                isActiveTelephone == that.isActiveTelephone &&
                isActiveInternational == that.isActiveInternational &&
                isActiveEcomerce == that.isActiveEcomerce &&
                limitTransactionDailyAtm == that.limitTransactionDailyAtm &&
                limitTransactionDailyPos == that.limitTransactionDailyPos &&
                limitTransactionDailyTelephone == that.limitTransactionDailyTelephone &&
                limitTransactionDailyInternational == that.limitTransactionDailyInternational &&
                limitTransactionDailyEcomerce == that.limitTransactionDailyEcomerce &&
                Objects.equals(pan, that.pan) &&
                Objects.equals(maskcard, that.maskcard) &&
                Objects.equals(seed, that.seed) &&
                Objects.equals(limitamountOperationAtm, that.limitamountOperationAtm) &&
                Objects.equals(limitamountOperationPos, that.limitamountOperationPos) &&
                Objects.equals(limitamountOperationTelephone, that.limitamountOperationTelephone) &&
                Objects.equals(limitamountOperationInternational, that.limitamountOperationInternational) &&
                Objects.equals(limitamountOperationEcomerce, that.limitamountOperationEcomerce) &&
                Objects.equals(limitamountDailyAtm, that.limitamountDailyAtm) &&
                Objects.equals(limitamountDailyPos, that.limitamountDailyPos) &&
                Objects.equals(limitamountDailyTelephone, that.limitamountDailyTelephone) &&
                Objects.equals(limitamountDailyInternational, that.limitamountDailyInternational) &&
                Objects.equals(limitamountDailyEcomerce, that.limitamountDailyEcomerce) &&
                Objects.equals(dateCreated, that.dateCreated) &&
                Objects.equals(dateUpdate, that.dateUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPan, idUser, idBin, pan, maskcard, seed, statusPan, isActiveAtm, isActivePos, isActiveTelephone, isActiveInternational, isActiveEcomerce, limitamountOperationAtm, limitamountOperationPos, limitamountOperationTelephone, limitamountOperationInternational, limitamountOperationEcomerce, limitamountDailyAtm, limitamountDailyPos, limitamountDailyTelephone, limitamountDailyInternational, limitamountDailyEcomerce, limitTransactionDailyAtm, limitTransactionDailyPos, limitTransactionDailyTelephone, limitTransactionDailyInternational, limitTransactionDailyEcomerce, dateCreated, dateUpdate);
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false, insertable = false, updatable = false)
    public TblEnrollmentUsersEntity getTblEnrollmentUsersByIdUser() {
        return tblEnrollmentUsersByIdUser;
    }

    public void setTblEnrollmentUsersByIdUser(TblEnrollmentUsersEntity tblEnrollmentUsersByIdUser) {
        this.tblEnrollmentUsersByIdUser = tblEnrollmentUsersByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_bin", referencedColumnName = "id_bin", nullable = false, insertable = false, updatable = false)
    public CatBinesEntity getCatBinesByIdBin() {
        return catBinesByIdBin;
    }

    public void setCatBinesByIdBin(CatBinesEntity catBinesByIdBin) {
        this.catBinesByIdBin = catBinesByIdBin;
    }
}
