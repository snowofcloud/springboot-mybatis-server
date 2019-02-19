package com.q8888.springboot.mybatis.server.controller;

import com.q8888.springboot.mybatis.server.service.IMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther xuxq
 * @date 2019/2/19 13:33
 */
@Api(tags = "发送邮件服务")
@RestController
@RequestMapping("api/mailService")
public class SendemailApplicationController {
    /**
     * 注入发送邮件的接口
     */
    @Autowired
    private IMailService mailService;
    /**
     * 测试发送文本邮件
     */
    @ApiOperation("发送怕普通邮件服务")
    @ResponseBody
    @GetMapping("/sendmail")
    public void sendmail() {
        mailService.sendSimpleMail("xuxq1015@163.com","主题：你好普通邮件","内容：第一封邮件");
    }

    @ApiOperation("发送怕html邮件服务")
    @ResponseBody
    @GetMapping("/sendmailHtml")
    public void sendmailHtml(){
        mailService.sendHtmlMail("xuxq1015@163.com","主题：你好html邮件","内容：第一封html邮件");
    }

}
