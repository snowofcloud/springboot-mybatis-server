package com.q8888.springboot.mybatis.server.controller;

import com.q8888.springboot.mybatis.server.base.JsonResult;
import com.q8888.springboot.mybatis.server.entity.Area;
import com.q8888.springboot.mybatis.server.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

import static com.q8888.springboot.mybatis.server.base.JsonResult.State.SUCCESS;

/**
 * @auther xuxq
 * @date 2019/2/16 12:21
 */
@Api(tags = "区域表")
@RestController
@RequestMapping("api/area")
public class AeraController {

    @Autowired
    private AreaService areaService;

    /**
     * 根据区域id查询区域名称
     * @param id
     * @return
     */
    @ApiOperation("根据区域id查询区域名称")
    @GetMapping("/queryAreaById/{id}")
    public JsonResult queryArea(@ApiParam(name = "id", value = "区域id", required = true)
                                @NotEmpty(message = "区域id不能为空") @PathVariable("id") Long id){
        Area area = areaService.selectById(id);
        return new JsonResult(SUCCESS,area);
    }

    @ApiOperation("简单测试练习")
    @GetMapping("/jian")
    public JsonResult eerr(){
        //测试热部署用的
        int a = 6666456;
        System.out.println(a);
        return new JsonResult(SUCCESS,a);
    }

}
