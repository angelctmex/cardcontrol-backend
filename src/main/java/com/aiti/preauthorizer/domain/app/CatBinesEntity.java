package com.aiti.preauthorizer.domain.app;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by zeus on 9/30/16.
 */
@Entity
@Table(name = "cat_bines", schema = "public", catalog = "preauthjava")
public class CatBinesEntity {
    private long idBin;
    private String bin;
    private String product;
    private String productName;
    private int enableBinSegment;
    private int timelifeSeed;
    private int timelifeCvv;
    private Date trandate;
    private Time trantime;
    private Collection<CatConfigEntity> catConfigsByIdBin;
    private Collection<TblCardsEntity> tblCardsByIdBin;

    @Id
    @Column(name = "id_bin")
    public long getIdBin() {
        return idBin;
    }

    public void setIdBin(long idBin) {
        this.idBin = idBin;
    }

    @Basic
    @Column(name = "bin")
    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    @Basic
    @Column(name = "product")
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "enable_bin_segment")
    public int getEnableBinSegment() {
        return enableBinSegment;
    }

    public void setEnableBinSegment(int enableBinSegment) {
        this.enableBinSegment = enableBinSegment;
    }

    @Basic
    @Column(name = "timelife_seed")
    public int getTimelifeSeed() {
        return timelifeSeed;
    }

    public void setTimelifeSeed(int timelifeSeed) {
        this.timelifeSeed = timelifeSeed;
    }

    @Basic
    @Column(name = "timelife_cvv")
    public int getTimelifeCvv() {
        return timelifeCvv;
    }

    public void setTimelifeCvv(int timelifeCvv) {
        this.timelifeCvv = timelifeCvv;
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
        CatBinesEntity that = (CatBinesEntity) o;
        return idBin == that.idBin &&
                enableBinSegment == that.enableBinSegment &&
                timelifeSeed == that.timelifeSeed &&
                timelifeCvv == that.timelifeCvv &&
                Objects.equals(bin, that.bin) &&
                Objects.equals(product, that.product) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(trandate, that.trandate) &&
                Objects.equals(trantime, that.trantime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBin, bin, product, productName, enableBinSegment, timelifeSeed, timelifeCvv, trandate, trantime);
    }

    @OneToMany(mappedBy = "catBinesByIdBin")
    public Collection<CatConfigEntity> getCatConfigsByIdBin() {
        return catConfigsByIdBin;
    }

    public void setCatConfigsByIdBin(Collection<CatConfigEntity> catConfigsByIdBin) {
        this.catConfigsByIdBin = catConfigsByIdBin;
    }

    @OneToMany(mappedBy = "catBinesByIdBin")
    public Collection<TblCardsEntity> getTblCardsByIdBin() {
        return tblCardsByIdBin;
    }

    public void setTblCardsByIdBin(Collection<TblCardsEntity> tblCardsByIdBin) {
        this.tblCardsByIdBin = tblCardsByIdBin;
    }
}
