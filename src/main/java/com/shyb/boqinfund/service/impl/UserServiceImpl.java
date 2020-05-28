package com.shyb.boqinfund.service.impl;

import com.shyb.boqinfund.entity.User;
import com.shyb.boqinfund.entity.UserExample;
import com.shyb.boqinfund.mapper.UserMapper;
import com.shyb.boqinfund.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author wzh
 * @date 2019/8/15 - 17:54
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserExample, User, UserMapper> implements UserService {
}
