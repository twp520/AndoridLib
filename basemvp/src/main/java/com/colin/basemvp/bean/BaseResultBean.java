package com.colin.basemvp.bean;

/**
 * Created by tianweiping on 2017/11/1.
 */

public class BaseResultBean<T> {

    private String RETMSG;// 提示信息

    private String RETCODE;// 响应码  00000 成功

    private String RETTRANSDATE;// 相应时间

    private T ARRAYDATA;

    public String getRETMSG() {
        return RETMSG;
    }

    public void setRETMSG(String RETMSG) {
        this.RETMSG = RETMSG;
    }

    public String getRETCODE() {
        return RETCODE;
    }

    public void setRETCODE(String RETCODE) {
        this.RETCODE = RETCODE;
    }

    public String getRETTRANSDATE() {
        return RETTRANSDATE;
    }

    public void setRETTRANSDATE(String RETTRANSDATE) {
        this.RETTRANSDATE = RETTRANSDATE;
    }

    public T getARRAYDATA() {
        return ARRAYDATA;
    }

    public void setARRAYDATA(T ARRAYDATA) {
        this.ARRAYDATA = ARRAYDATA;
    }
}
