package com.shyb.boqinfund.controller;

import com.shyb.boqinfund.entity.FundPrice;
import com.shyb.boqinfund.entity.FundPriceExample;
import com.shyb.boqinfund.service.FundPriceService;
import com.shyb.boqinfund.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author wzh
 * @date 2019/8/16 - 10:02
 */
@Controller
@RequestMapping("/fund")
public class FundPriceController {
    @Autowired
    FundPriceService fundPriceService;
    public static final Map<String,String> MAP = new HashMap<>();
    @RequestMapping("showFundPrice")
    @ResponseBody
    public Map<String,Object> showFundPrice(){
        FundPriceExample example = new FundPriceExample();
        example.setOrderByClause("id desc");
        example.setLimitStart(0);
        example.setLimitEnd(30);
        List<FundPrice> fundPrices = fundPriceService.select(example);
        List<String> dates = new ArrayList<String>();
        List<String> prices = new ArrayList<String>();
        Collections.reverse(fundPrices);
        for (FundPrice fundPrice : fundPrices) {
            dates.add(DateUtils.format(fundPrice.getCtime(),"yyyy-MM-dd"));
            prices.add(fundPrice.getPrice().stripTrailingZeros().toPlainString());
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("dates",dates);
        map.put("prices",prices);
        map.put("yearEarn",MAP.get("yearEarn") == null ? saveYearEarn() : MAP.get("yearEarn"));
        map.put("dayEarn",MAP.get("dayEarn") == null ? saveDayEarn() : MAP.get("dayEarn"));
        return map;
    }

    @RequestMapping("showAllFundPrice")
    public String showAllFundPrice(ModelMap modelMap) throws InterruptedException {
        FundPriceExample example = new FundPriceExample();
        example.setOrderByClause("id desc");
        List<FundPrice> fundPrices = fundPriceService.select(example);
        modelMap.addAttribute("fundPrices",fundPrices);
        return "showAllFundPrice";
    }
    @RequestMapping("add")
    public String add(ModelMap modelMap){
        modelMap.addAttribute("ctime",DateUtils.format(new Date(),"yyyy-MM-dd"));
        return "fundPrice";
    }

    @RequestMapping("update")
    public String update(int id,ModelMap modelMap){
        FundPrice fundPrice = fundPriceService.selectByKey(id);
        modelMap.addAttribute("fundPrice",fundPrice);
        return "fundPrice";
    }

    @RequestMapping("del")
    public String del(int id){
       fundPriceService.deleteByKey(id);
        return "redirect:/fund/showAllFundPrice";
    }

    @RequestMapping("addOrUpdate")
    public String addOrUpdate(@RequestParam(required = false,defaultValue = "") String id,String price,String ctime,ModelMap modelMap){
        Date date = DateUtils.parseDate(ctime);
        FundPriceExample example = new FundPriceExample();
        example.createCriteria().andCtimeEqualTo(date);
        if (fundPriceService.select(example).size()>0 && StringUtils.isBlank(id)) {
            modelMap.addAttribute("error_msg",String.format("%s时间段已有数据",ctime));
            return "fundPrice";
        }
        FundPrice fundPrice = new FundPrice();
        fundPrice.setPrice(new BigDecimal(price));
        fundPrice.setCtime(date);
        if (StringUtils.isBlank(id)) {
            fundPriceService.add(fundPrice);
        }else{
            fundPrice.setId(Integer.parseInt(id));
            fundPriceService.updateByKey(fundPrice);
        }
        saveYearEarn();
        saveDayEarn();
        return "redirect:/fund/showAllFundPrice";
    }

    private String saveYearEarn(){
        String yearEarn = fundPriceService.getYearEarn();
        MAP.put("yearEarn",yearEarn);
        return yearEarn;
    }

    private String saveDayEarn(){
        String dayEarn = fundPriceService.getDeyEarn();
        MAP.put("dayEarn",dayEarn);
        return dayEarn;
    }
}
