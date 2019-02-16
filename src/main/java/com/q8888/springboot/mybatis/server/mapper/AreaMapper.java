package com.q8888.springboot.mybatis.server.mapper;

import com.q8888.springboot.mybatis.server.entity.Area;

/**
 * @auther xuxq
 * @date 2019/2/16 12:27
 */
public interface AreaMapper {
    Area selectById(Long id);
}
