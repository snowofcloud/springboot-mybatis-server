package com.q8888.springboot.mybatis.server.service.impl;

import com.q8888.springboot.mybatis.server.common.exception.MybatisServerException;
import com.q8888.springboot.mybatis.server.entity.Area;
import com.q8888.springboot.mybatis.server.mapper.AreaMapper;
import com.q8888.springboot.mybatis.server.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.q8888.springboot.mybatis.server.base.Constants.DATE_FORMAT_01;
import static com.q8888.springboot.mybatis.server.base.JsonResult.State.SYSTEM_ERROR;

/**
 * @auther xuxq
 * @date 2019/2/16 12:26
 */
@Service(value = "areaService")
@Slf4j
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public Area selectById(Long id) {
        return areaMapper.selectById(id);
    }

    @Override
    public Area queryByDate(String createTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_01);
        Date parse = null;
        try {
            parse = sdf.parse(createTime);
        } catch (ParseException e) {
            //异常处理和打印日志
            log.error("Date ParseException{}",e.getMessage());
            throw new MybatisServerException(SYSTEM_ERROR);
        }
        return areaMapper.queryAreaByDate(parse);
    }
}
