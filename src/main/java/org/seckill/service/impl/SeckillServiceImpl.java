package org.seckill.service.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;


/**
 * @author Aaron
 * @since 2020/6/23 20:22
 */
public class SeckillServiceImpl implements SeckillService {
    // md5盐值,用于混淆
    private final String slat = "salijr32ujjeopujOPIUWPOIUU#WO#(*#(*IKQKLJDFL:KSJDGH";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    public List<Seckill> getSeckillList(){
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId){
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId){
        Seckill seckill = seckillDao.queryById(seckillId);
        if(seckill == null)
            return new Exposer(false,seckillId);
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if(nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()){
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5();
        return new Exposer(true, md5, seckillId);
    }

    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5){
        if(md5 == null || !md5.equals(getMD5(seckillId)))
            throw new SeckillException("seckill data rewrite");

        Date now = new Date();
        try {
            int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
            if(insertCount <= 0)
                throw new RepeatKillException("seckill repeated");

            int updateCount = seckillDao.reduceNumber(seckillId, now);
            if(updateCount <= 0)
                throw new SeckillCloseException("seckill is closed");

            SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
            return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
        }catch (SeckillException e){
            throw e;
        }catch (Exception e){
            //为什么要把所有的异常都转成运行时异常？
            //现在既有throw又有catch，到底是哪里捕获异常？
            logger.error(e.getMessage(),e);
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }

    private String getMD5(long seckillId){
        String base = seckillId + "/" + slat;
        //这个Spring的工具包里有什么
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

}
