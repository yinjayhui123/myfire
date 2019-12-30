package com.yh.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: yinhui
 * @Date: 2019/4/24 13:41
 * @Version 1.0
 */
public class WorkName extends WorkNameFather implements Serializable {

    /**
     * id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 业务名称
     *
     * @mbggenerated
     */
    private String workName;

    /**
     * 业务主管姓名
     *
     * @mbggenerated
     */
    private String chargeName;

    /**
     * 业务主管邮件
     *
     * @mbggenerated
     */
    private String chargeMail;


    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName == null ? null : workName.trim();
    }

    public String getChargeName() {
        return chargeName;
    }

    private void setChargeName(String chargeName) {
        this.chargeName = chargeName == null ? null : chargeName.trim();
    }

    public String getChargeMail() {
        return chargeMail;
    }

    public void setChargeMail(String chargeMail) {
        this.chargeMail = chargeMail == null ? null : chargeMail.trim();
    }

}
