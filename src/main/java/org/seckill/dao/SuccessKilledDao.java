package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * @author Aaron
 * @since 2020/6/20 9:15
 */
public interface SuccessKilledDao {
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

    /**
    * @Description: 通过ID查询所有秒杀成功记录
    * @Param: [seckillId, userPhone] 秒杀ID，用户手机号
    * @return: org.seckill.entity.SuccessKilled
    */
    SuccessKilled queryByIdWithSeckill( long seckillId,  long userPhone);
}
