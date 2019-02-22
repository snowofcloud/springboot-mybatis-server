package com.q8888.springboot.mybatis.server.common.exception;

import com.q8888.springboot.mybatis.server.base.JsonResult;
import lombok.Data;

/**
 * @auther xuxq
 * @date 2019/2/22 22:06
 */
@Data
public class MybatisServerException extends RuntimeException {
    private static final long serialVersionUID = 4564124491192825231L;

    private JsonResult.State state;

    public MybatisServerException() {
        super();
    }

    public MybatisServerException (JsonResult.State state) {
        super();
        this.setState(state);
    }

    public MybatisServerException (JsonResult.State state, String message) {
        super(message);
        this.setState(state);
    }

}
