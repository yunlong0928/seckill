package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

/**
 * @author Aaron
 * @since 2020/6/20 9:15
 */
public interface SuccessKilledDao {
    int insertSuccessKilled(long seckillId, long userPhone);
    SuccessKilled queryByIdWithSeckill(long seckillId);
}
