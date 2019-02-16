package com.q8888.springboot.mybatis.server.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @auther xuxq
 * @date 2019/2/16 18:03
 */
@Data
@AllArgsConstructor
public class Page<T> {

    /**
     * 当前页列表
     */
    private List<T> list;

    /**
     * 总数
     */
    private Integer total;

    /**
     * 页号
     */
    private Integer pageNo;

    /**
     * 页尺寸
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer pageTotal;
}
