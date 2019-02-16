package com.q8888.springboot.mybatis.server.service;

import com.q8888.springboot.mybatis.server.entity.User;

import java.util.List;

/**
 * @auther xuxq
 * @date 2019/2/15 19:40
 */
public interface UserService {
    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);

}
