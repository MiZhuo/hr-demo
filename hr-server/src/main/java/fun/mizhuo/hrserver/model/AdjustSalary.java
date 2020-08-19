package fun.mizhuo.hrserver.model;

import java.io.Serializable;
import java.util.Date;

public class AdjustSalary implements Serializable {
    private Integer id;

    private Integer eid;

    private Date asdate;

    private Integer beforesalary;

    private Integer aftersalary;

    private String reason;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Date getAsdate() {
        return asdate;
    }

    public void setAsdate(Date asdate) {
        this.asdate = asdate;
    }

    public Integer getBeforesalary() {
        return beforesalary;
    }

    public void setBeforesalary(Integer beforesalary) {
        this.beforesalary = beforesalary;
    }

    public Integer getAftersalary() {
        return aftersalary;
    }

    public void setAftersalary(Integer aftersalary) {
        this.aftersalary = aftersalary;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AdjustSalary other = (AdjustSalary) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEid() == null ? other.getEid() == null : this.getEid().equals(other.getEid()))
            && (this.getAsdate() == null ? other.getAsdate() == null : this.getAsdate().equals(other.getAsdate()))
            && (this.getBeforesalary() == null ? other.getBeforesalary() == null : this.getBeforesalary().equals(other.getBeforesalary()))
            && (this.getAftersalary() == null ? other.getAftersalary() == null : this.getAftersalary().equals(other.getAftersalary()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEid() == null) ? 0 : getEid().hashCode());
        result = prime * result + ((getAsdate() == null) ? 0 : getAsdate().hashCode());
        result = prime * result + ((getBeforesalary() == null) ? 0 : getBeforesalary().hashCode());
        result = prime * result + ((getAftersalary() == null) ? 0 : getAftersalary().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", eid=").append(eid);
        sb.append(", asdate=").append(asdate);
        sb.append(", beforesalary=").append(beforesalary);
        sb.append(", aftersalary=").append(aftersalary);
        sb.append(", reason=").append(reason);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}