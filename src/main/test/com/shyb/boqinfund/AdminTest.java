package com.shyb.boqinfund;

import com.shyb.boqinfund.entity.Admin;
import com.shyb.boqinfund.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wzh
 * @date 2019/8/15 - 15:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest {
    @Autowired
    AdminService adminService;
    @Test
    public void test(){
        adminService.add(new Admin().setUsername("222").setPassword("222"));
    }

}
