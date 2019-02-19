package com.q8888.springboot.mybatis.server.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.q8888.springboot.mybatis.server.base.Constants.P_MOBILE_PHONE;

@ApiModel(value = "user", description = "用户信息管理")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer userId;

    @NotBlank(message = "用户姓名不能为空")
    @ApiModelProperty(name = "userName",value = "用户姓名",required = true)
    private String userName;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(name = "password",value = "用户密码",required = true)
    private String password;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = P_MOBILE_PHONE, message = "请输入正确的手机号码")
    @ApiModelProperty(name = "phone",value = "用户手机号",required = true)
    private String phone;

    @NotNull(message = "用户状态不能为空")
    @ApiModelProperty(name = "status",value = "用户状态",required = true)
    private Integer status;

}