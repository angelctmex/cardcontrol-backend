package com.aiti.preauthorizer.domain.app;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_trx_log", schema = "public", catalog = "preauthjava")
public class TblTrxLogEntity {
    private int logid;
    private String logcomponent;
    private String logdescription;
    private String logdata;
    private int logcardid;
    private String logcardnumber;
    private Timestamp logdate;

    @Id
    @Column(name = "logid", nullable = false)
    public int getLogid() {
        return logid;
    }

    public void setLogid(int logid) {
        this.logid = logid;
    }

    @Basic
    @Column(name = "logcomponent", nullable = false)
    public String getLogcomponent() {
        return logcomponent;
    }

    public void setLogcomponent(String logcomponent) {
        this.logcomponent = logcomponent;
    }

    @Basic
    @Column(name = "logdescription", nullable = false)
    public String getLogdescription() {
        return logdescription;
    }

    public void setLogdescription(String logdescription) {
        this.logdescription = logdescription;
    }

    @Basic
    @Column(name = "logdata", nullable = false)
    public String getLogdata() {
        return logdata;
    }

    public void setLogdata(String logdata) {
        this.logdata = logdata;
    }

    @Basic
    @Column(name = "logcardid", nullable = false)
    public int getLogcardid() {
        return logcardid;
    }

    public void setLogcardid(int logcardid) {
        this.logcardid = logcardid;
    }

    @Basic
    @Column(name = "logcardnumber", nullable = false)
    public String getLogcardnumber() {
        return logcardnumber;
    }

    public void setLogcardnumber(String logcardnumber) {
        this.logcardnumber = logcardnumber;
    }

    @Basic
    @Column(name = "logdate", nullable = false)
    public Timestamp getLogdate() {
        return logdate;
    }

    public void setLogdate(Timestamp logdate) {
        this.logdate = logdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblTrxLogEntity that = (TblTrxLogEntity) o;

        if (logid != that.logid) return false;
        if (logcomponent != null ? !logcomponent.equals(that.logcomponent) : that.logcomponent != null) return false;
        if (logdescription != null ? !logdescription.equals(that.logdescription) : that.logdescription != null) return false;
        if (logdata != null ? !logdata.equals(that.logdata) : that.logdata != null) return false;
        if (logcardid != that.logcardid) return false;
        if (logcardnumber != null ? !logcardnumber.equals(that.logcardnumber) : that.logcardnumber != null) return false;
        if (logdate != null ? !logdate.equals(that.logdate) : that.logdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (logid);
        result = 31 * result + (logcomponent != null ? logcomponent.hashCode() : 0);
        result = 31 * result + (logdescription != null ? logdescription.hashCode() : 0);
        result = 31 * result + (logdata != null ? logdata.hashCode() : 0);
        result = 31 * result + logcardid;
        result = 31 * result + (logcardnumber != null ? logcardnumber.hashCode() : 0);
        result = 31 * result + (logdate != null ? logdate.hashCode() : 0);

        return result;
    }
}
