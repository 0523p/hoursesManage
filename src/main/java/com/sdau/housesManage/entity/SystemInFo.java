package com.sdau.housesManage.entity;


public class SystemInFo {
    private Integer id;

    private String name;

    private String address;

    private String area;

    private Integer fnum;

    private String ctlname;

    private String iphone;

    private String remark;

    private String shownote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Integer getFnum() {
        return fnum;
    }

    public void setFnum(Integer fnum) {
        this.fnum = fnum;
    }

    public String getCtlname() {
        return ctlname;
    }

    public void setCtlname(String ctlname) {
        this.ctlname = ctlname == null ? null : ctlname.trim();
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone == null ? null : iphone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getShownote() {
        return shownote;
    }

    public void setShownote(String shownote) {
        this.shownote = shownote == null ? null : shownote.trim();
    }
}