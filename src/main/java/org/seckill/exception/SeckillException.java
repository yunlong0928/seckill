package org.seckill.exception;

/**
 * @author Aaron
 * @since 2020/6/23 20:12
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
