package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;
import lombok.*;

/**
 * @Auther: seadragon
 * @Date: 2019-06-08 17:29
 * @Description:
 */

@Data
public class ExceptionResult {

    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status    = em.getCode();
        this.message   = em.getMessage();
        this.timestamp = System.currentTimeMillis();
    }
}
