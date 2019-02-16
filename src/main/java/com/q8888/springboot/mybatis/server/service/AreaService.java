package com.q8888.springboot.mybatis.server.service;

import com.q8888.springboot.mybatis.server.entity.Area;

/**
 * @auther xuxq
 * @date 2019/2/16 12:24
 */
public interface AreaService {
    Area selectById(Long id);
}
