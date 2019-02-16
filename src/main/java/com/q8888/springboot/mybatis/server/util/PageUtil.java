package com.q8888.springboot.mybatis.server.util;

import com.q8888.springboot.mybatis.server.base.PageInfo;

/**
 * Created by huangr on 2018/9/30.
 */
public class PageUtil<T> {

    //自己构建的轮子
    public static <T> PageInfo page2PageInfo(com.q8888.springboot.mybatis.server.base.Page<T> page) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(page.getList());
        pageInfo.setCount(page.getTotal().longValue());
        pageInfo.setPageIndex(page.getPageNo());
        pageInfo.setPageSize(page.getPageSize());
        pageInfo.setPageTotal(page.getPageTotal().longValue());
        return pageInfo;
    }
    /*用分页插件*/
    //
//    public static <T> PageInfo mybatisPage2PageInfo(com.baomidou.mybatis.plugins.Page<T> page) {
//        PageInfo pageInfo = new PageInfo();
//        pageInfo.setCount(page.getTotal());
//        pageInfo.setPageIndex(page.getCurrent());
//        pageInfo.setList(page.getRecords());
//        pageInfo.setPageSize(page.getSize());
//        pageInfo.setPageTotal(page.getPages());
//        return pageInfo;
//    }
}
