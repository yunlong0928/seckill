package org.seckill.exception;

/**
 * @author Aaron
 * @since 2020/6/23 20:12
 */

//为什么这里没有构造函数会报错？继承后必须写构造函数吗
public class RepeatKillException extends SeckillException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
