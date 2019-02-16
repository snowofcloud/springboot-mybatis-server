package com.q8888.springboot.mybatis.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.q8888.springboot.mybatis.server.entity.User;
import com.q8888.springboot.mybatis.server.mapper.UserMapper;
import com.q8888.springboot.mybatis.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther xuxq
 * @date 2019/2/15 19:42
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }
}
