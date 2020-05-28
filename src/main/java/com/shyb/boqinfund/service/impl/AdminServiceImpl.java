package com.shyb.boqinfund.service.impl;

import com.shyb.boqinfund.entity.Admin;
import com.shyb.boqinfund.entity.AdminExample;
import com.shyb.boqinfund.mapper.AdminMapper;
import com.shyb.boqinfund.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @author wzh
 * @date 2019/8/15 - 16:35
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminExample, Admin,AdminMapper> implements AdminService{
}
