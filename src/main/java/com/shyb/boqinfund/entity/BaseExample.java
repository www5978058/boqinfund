package com.shyb.boqinfund.entity;

/**
 * @author wzh
 * @date 2019/8/15 - 15:50
 */
public class BaseExample {
    protected Integer limitStart;
    protected Integer limitEnd;

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart = limitStart;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd = limitEnd;
    }
}
