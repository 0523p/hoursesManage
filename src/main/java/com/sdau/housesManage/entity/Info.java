package com.sdau.housesManage.entity;

import java.util.Date;

public class Info  {
    private Integer id;

    private String title;

    private Date date;

    private String status;

    private String type;

    private Integer userid;

    private String ctlusername;

    private String remark;

    private String note;

    private String resultnote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCtlusername() {
        return ctlusername;
    }

    public void setCtlusername(String ctlusername) {
        this.ctlusername = ctlusername == null ? null : ctlusername.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getResultnote() {
        return resultnote;
    }

    public void setResultnote(String resultnote) {
        this.resultnote = resultnote == null ? null : resultnote.trim();
    }
}