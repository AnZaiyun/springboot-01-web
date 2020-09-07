package com.anzaiyun.dao;

import com.anzaiyun.util.ChineseNamesAnno;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@ToString
public class UserDao {
    @ChineseNamesAnno(ChineseName = "唯一标识符")
    private int L_ID;

    @ChineseNamesAnno(ChineseName = "用户名")
    private String VC_NAME;

    @ChineseNamesAnno(ChineseName = "性别（1：男；2：女）")
    private int L_SEX;

    @ChineseNamesAnno(ChineseName = "电话")
    private String L_TEL;

    @ChineseNamesAnno(ChineseName = "邮箱")
    private String VC_MAIL;

    @ChineseNamesAnno(ChineseName = "地址")
    private String VC_ADDRESS;

    @ChineseNamesAnno(ChineseName = "时间")
    private Date D_JRSJ;

    @ChineseNamesAnno(ChineseName = "状态")
    private int L_STATE;

    public int getL_ID() {
        return L_ID;
    }

    public void setL_ID(int l_ID) {
        L_ID = l_ID;
    }

    public String getVC_NAME() {
        return VC_NAME;
    }

    public void setVC_NAME(String VC_NAME) {
        this.VC_NAME = VC_NAME;
    }

    public int getL_SEX() {
        return L_SEX;
    }

    public void setL_SEX(int l_SEX) {
        L_SEX = l_SEX;
    }

    public String getL_TEL() {
        return L_TEL;
    }

    public void setL_TEL(String l_TEL) {
        L_TEL = l_TEL;
    }

    public String getVC_MAIL() {
        return VC_MAIL;
    }

    public void setVC_MAIL(String VC_MAIL) {
        this.VC_MAIL = VC_MAIL;
    }

    public String getVC_ADDRESS() {
        return VC_ADDRESS;
    }

    public void setVC_ADDRESS(String VC_ADDRESS) {
        this.VC_ADDRESS = VC_ADDRESS;
    }

    public Date getD_JRSJ() {
        return D_JRSJ;
    }

    public void setD_JRSJ(Date d_JRSJ) {
        D_JRSJ = d_JRSJ;
    }

    public int getL_STATE() {
        return L_STATE;
    }

    public void setL_STATE(int l_STATE) {
        L_STATE = l_STATE;
    }
}
