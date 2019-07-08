package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auther: seadragon
 * @Date: 2019-06-22 22:27
 * @Description:
 */

@Data
@Table(name = "tb_stock")
public class Stock {
    @Id
    private Long skuId;
    private Integer stock;//正常库存
    private Integer seckillStock; //秒杀可用库存
    private Integer seckillTotal; //已秒杀数量
}
