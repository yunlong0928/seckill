package org.seckill.dao;

import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * @author Aaron
 * @since 2020/6/20 9:07
 */
public interface SeckillDao {
    int reduceNumber(long seckillId, Date killTime);

    Seckill queryById(long seckillId);

    List<Seckill> queryAll(int offet, int limit);
}
