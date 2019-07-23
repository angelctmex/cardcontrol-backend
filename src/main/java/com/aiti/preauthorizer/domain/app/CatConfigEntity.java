package com.aiti.preauthorizer.domain.app;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Created by zeus on 6/10/19.
 */
@Entity
@Table(name = "cat_config", schema = "public", catalog = "preauthjava")
@IdClass(CatConfigEntityPK.class)
public class  CatConfigEntity {
    private long idConf;
    private long idBin;
    private String name;
    private String value;
    private String description;
    private String type;
    private Date trandate;
    private Time trantime;
    private CatBinesEntity catBinesByIdBin;

    @Id
    @Column(name = "id_conf")
    public long getIdConf() {
        return idConf;
    }

    public void setIdConf(long idConf) {
        this.idConf = idConf;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "trandate")
    public Date getTrandate() {
        return trandate;
    }

    public void setTrandate(Date trandate) {
        this.trandate = trandate;
    }

    @Basic
    @Column(name = "trantime")
    public Time getTrantime() {
        return trantime;
    }

    public void setTrantime(Time trantime) {
        this.trantime = trantime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatConfigEntity that = (CatConfigEntity) o;
        return idConf == that.idConf &&
                idBin == that.idBin &&
                Objects.equals(name, that.name) &&
                Objects.equals(value, that.value) &&
                Objects.equals(description, that.description) &&
                Objects.equals(type, that.type) &&
                Objects.equals(trandate, that.trandate) &&
                Objects.equals(trantime, that.trantime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConf, idBin, name, value, description, type, trandate, trantime);
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
