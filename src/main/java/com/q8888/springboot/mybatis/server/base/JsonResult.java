package com.q8888.springboot.mybatis.server.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

import static com.q8888.springboot.mybatis.server.base.Constants.DATE_FORMAT_04;

/**
 * @auther xuxq
 * @date 2018/11/23 11:18
 */
@Data
@ApiModel(value = "JsonResult", description = "rest接口统一返回格式")
public class JsonResult implements Serializable {

    @ApiModelProperty(name = "code", value = "错误状态码（非http请求码）", example = "0")
    private int code;

    @ApiModelProperty(name = "status", value = "状态码true/false", example = "true")
    private boolean status;

    @ApiModelProperty(name = "message", value = "错误描述", example = "success")
    private String message;

    @ApiModelProperty(name = "data", value = "数据体", example = "{\"key\":\"value\"}")
    private Object data;

    @ApiModelProperty(name = "timestamp", value = "时间戳", example = "2018-11-23 12:34:56")
    @JsonFormat(pattern = DATE_FORMAT_04, timezone = "GMT+8")
    private Date timestamp;

    /**
     * 私有构造方法，以供其他构造函数调用
     * @param code
     * @param status
     * @param message
     * @param data
     */
    private JsonResult(int code, boolean status, String message, Object data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = new Date();
    }

    public JsonResult(State state){
        this(state.getCode(), state.isStatus(), state.getMessage(), null);
    }
    public static JsonResult success(){
        return new JsonResult(State.SUCCESS);
    }
    public static JsonResult success(Object data){
        return new JsonResult(State.SUCCESS,data);
    }
    public static JsonResult failed(String message){
        return  new JsonResult(State.FAILED,message);
    }
    public static JsonResult failed(String message,Object data){
        return  new JsonResult(State.FAILED,message,data);
    }
    //无参构造
    private JsonResult(){}
    //常用构造
    public JsonResult(State state, String message){
        this(state.getCode(),state.isStatus(),message,null);
    }
    public JsonResult(State state, Object data){
        this(state.getCode(), state.isStatus(), state.getMessage(), data);
    }
    public JsonResult(State state, String message, Object data){
        this(state.getCode(), state.isStatus(), message, data);
    }



    @Getter
    @AllArgsConstructor
    public enum State {

        SUCCESS(0, true, "success"),
        FAILED(1,false,"failed"),
        SYSTEM_ERROR(9999, false, "System error"),
        SERVER_COMMON_ERROR(1, false, "Non compliance with business rules"),
        INVALID_PARAM(1001, false, "Invalid param"),
        INVALID_PATH(1002, false, "Invalid path"),
        EMPTY_UAC_TOKEN(1003, false, "Empty UAC token"),
        INVALID_UAC_TOKEN(1004, false, "Invalid UAC token"),
        UAC_API_ERROR(1005, false, "UAC api error"),
        UAC_API_FAIL(1006, false, "UAC api fail"),
        BASE_API_ERROR(1007, false, "BASE api error"),
        BASE_API_FAIL(1008, false, "BASE api fail"),
        DDSE_API_ERROR(1011, false, "DDSE api error"),
        DDSE_API_FAIL(1012, false, "DDSE api fail"),
        ALARM_API_ERROR(1013, false, "Alarm api error"),
        ALARM_API_FAIL(1014, false, "Alarm api fail"),
        SEARCH_WORD_WRONG(1015, false, "search内容错误！"),

        UPLOAD_EMPTY_FILE(2001, false, "上传附件不能为空！"),
        UPLOAD_FILE_FAIL(2002, false, "上传失败！"),
        ADD_FAIL(2003, false, "保存失败，请检查填写信息是否有误！"),
        FILLTIME_REPETITION(2005, false,"该月份的月报已存在，请重新选择填报时间。"),
        UPDATE_FAIL(2004, false, "修改失败，请检查修改信息是否有误！"),
        DELETE_FAIL(2007, false, "删除失败，请检查删除信息是否有误！"),
        QUERY_FAIL(2008, false, "获取失败，请检查获取信息是否有误！"),
        LOGIN_FAIL(2009, false, "登陆失败，请检查账户密码是否有误！"),
        EXIST_PLATE_NUMBER_FAIL(2005, false, "该名称已存在!"),
        USING_FAIL(2006, false, "该条已被使用无法删除或修改!"),

        INIT_FAIL(3001, false, "初始化失败！"),
        INIT_AGAIN(3002, false, "已初始化，无需再次初始化！"),

        NUMBER_RANGE_FAIL(4001, false, "数字范围有误！"),

        PARAM_EXIST_FAIL(5001, false, "该检测要素配置已经存在！");

        private int code;
        private boolean status;
        private String message;

    }




}
