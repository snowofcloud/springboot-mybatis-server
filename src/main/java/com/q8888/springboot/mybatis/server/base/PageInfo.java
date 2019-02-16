package com.q8888.springboot.mybatis.server.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 分页信息
 * @auther xuxq
 * @date 2018/9/29 13:37
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> implements Serializable {
    private List<T> list;//当前页数据
    private Integer pageIndex;//显示当前页码
    private Integer pageSize;//每页显示数据量
    private Long count;//当前页数据数量
    private Long pageTotal;//总页数

    public static <T> PageInfo<T> format(List<T> list, Integer pageIndex, Integer pageSize, Long count, Long pageTotal) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setList(list);
        pageInfo.setCount(count);
        pageInfo.setPageIndex(pageIndex);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageTotal(pageTotal);
        return pageInfo;
    }

}
