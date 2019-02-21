package com.q8888.springboot.mybatis.server.controller;

import com.q8888.springboot.mybatis.server.base.JsonResult;
import com.q8888.springboot.mybatis.server.entity.User;
import com.q8888.springboot.mybatis.server.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.q8888.springboot.mybatis.server.base.JsonResult.State.SUCCESS;

/**
 * @auther xuxq
 * @date 2019/2/15 19:39
 */
@Api(tags = "用户登陆")
@Slf4j //打印日志用的注解
@Controller
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;


    //RequestMapping方式
    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public JsonResult addUser(@Validated User user){
        log.info("addUser",user);
        return new JsonResult(SUCCESS,userService.addUser(user));
    }

    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public JsonResult findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        List<User> allUser = userService.findAllUser(pageNum, pageSize);
        log.info("findAllUser"+allUser.toString());
        log.error("findAllUser",allUser);
        log.debug("findAllUser",allUser);
        return new JsonResult(SUCCESS,allUser);
    }

    /**
     * 增加用户信息
     * @param user
     * @return
     */
//    @ApiOperation("增加用户信息")
//    @GetMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
//    public JsonResult addUser(User user){
//        return new JsonResult(JsonResult.State.SUCCESS,userService.addUser(user));
//    }

    /**
     * 获取用户信息
     * @param pageNum
     * @param pageSize
     * @return
     */
//    @ApiOperation("获取用户信息")
//    @GetMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
//    public JsonResult findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
//        return new JsonResult(JsonResult.State.SUCCESS,userService.findAllUser(pageNum,pageSize));
//    }


}
