package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * @author Aaron
 * @since 2020/6/21 16:55
 * 秒杀业务子类
 */
public interface SeckillService {
    /**
    * @Description: 查询所有秒杀记录
    * @Param: [无]
    * @return: java.util.List<org.seckill.entity.Seckill>秒杀记录
    */
    List<Seckill> getSeckillList();

    /**
    * @Description: 查询单个秒杀记录
    * @Param: [seckillId]秒杀ID
    * @return: org.seckill.entity.Seckill
    */
    Seckill getByID(long seckillId);

    /**
     * 秒杀是否开启:
     * 1.开启时:输出秒杀接口地址
     * 2.未开启:输出系统时间和秒杀时间
     *
     * @param seckillId id
     */
    Exposer exportSeckillUrl(long seckillId);

    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws RepeatKillException, SeckillCloseException, SeckillException;
    //SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);
}
