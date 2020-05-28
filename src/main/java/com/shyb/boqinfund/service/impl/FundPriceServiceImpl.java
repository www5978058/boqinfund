package com.shyb.boqinfund.service.impl;

import com.shyb.boqinfund.entity.FundPrice;
import com.shyb.boqinfund.entity.FundPriceExample;
import com.shyb.boqinfund.mapper.FundPriceMapper;
import com.shyb.boqinfund.service.FundPriceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wzh
 * @date 2019/8/15 - 17:53
 */
@Service
public class FundPriceServiceImpl extends BaseServiceImpl<FundPriceExample, FundPrice, FundPriceMapper> implements FundPriceService {
    @Override
    public String getYearEarn() {
        String yearEarn = "0%";
        FundPriceExample example = new FundPriceExample();
        example.setLimitStart(0);
        example.setLimitEnd(1);
        List<FundPrice> list = select(example);
        if (list.size() > 0) {
            BigDecimal start = list.get(0).getPrice();
            example.setOrderByClause("id desc");
            BigDecimal end = select(example).get(0).getPrice();
            yearEarn = end.subtract(start).multiply(new BigDecimal("100")).divide(start, 2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString() + "%";
        }
        return yearEarn;
    }

    @Override
    public String getDeyEarn() {
        String dayEarn = "0%";
        FundPriceExample example = new FundPriceExample();
        example.setLimitStart(0);
        example.setLimitEnd(2);
        example.setOrderByClause("id desc");
        List<FundPrice> list = select(example);
        if (list.size() == 2) {
            BigDecimal end = list.get(0).getPrice();
            BigDecimal start = list.get(1).getPrice();
            dayEarn = end.subtract(start).multiply(new BigDecimal("100")).divide(start, 2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString() + "%";
        }
        return dayEarn;
    }
}
