package com.aiti.preauthorizer.domain.app;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CatConfigEntityPK implements Serializable {
    private long idConf;
    private long idBin;

    @Column(name = "id_conf")
    @Id
    public long getIdConf() {
        return idConf;
    }

    public void setIdConf(long idConf) {
        this.idConf = idConf;
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
        CatConfigEntityPK that = (CatConfigEntityPK) o;
        return idConf == that.idConf &&
                idBin == that.idBin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConf, idBin);
    }
}
