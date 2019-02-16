package com.q8888.springboot.mybatis.server.entity;

import lombok.Data;

import java.util.Date;

/**
 * @auther xuxq
 * @date 2018/12/17 12:55
 */
@Data
public class Area{

    private Long id;
    /**
     * 区域名称
     */
    private String area;

    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;

}
