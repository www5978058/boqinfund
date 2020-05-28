package com.shyb.boqinfund.service;

import com.shyb.boqinfund.entity.FundPrice;
import com.shyb.boqinfund.entity.FundPriceExample;

/**
 * @author wzh
 * @date 2019/8/15 - 16:13
 */
public interface FundPriceService extends BaseService<FundPriceExample, FundPrice> {
    /**
     * 获取年收益
     * @return
     */
    public String getYearEarn();

    /**
     * 获取年收益
     * @return
     */
    public String getDeyEarn();
}
