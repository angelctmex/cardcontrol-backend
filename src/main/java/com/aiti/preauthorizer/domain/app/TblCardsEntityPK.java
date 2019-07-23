package com.aiti.preauthorizer.domain.app;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class TblCardsEntityPK implements Serializable {
    private long idPan;
    private long idUser;
    private long idBin;

    @Column(name = "id_pan")
    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="TBL_CARDS_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_sequence")
    public long getIdPan() {
        return idPan;
    }

    public void setIdPan(long idPan) {
        this.idPan = idPan;
    }

    @Column(name = "id_user")
    @Id
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Column(name = "id_bin")
    @Id
    public long getIdBin() {
        return idBin;
    }

    public void setIdBin(long idBin) {
        this.idBin = idBin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblCardsEntityPK that = (TblCardsEntityPK) o;
        return idPan == that.idPan &&
                idUser == that.idUser &&
                idBin == that.idBin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPan, idUser, idBin);
    }
}

