package com.q8888.springboot.mybatis.server.service.impl;

import com.q8888.springboot.mybatis.server.entity.Area;
import com.q8888.springboot.mybatis.server.mapper.AreaMapper;
import com.q8888.springboot.mybatis.server.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther xuxq
 * @date 2019/2/16 12:26
 */
@Service(value = "areaService")
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public Area selectById(Long id) {
        return areaMapper.selectById(id);
    }
}
