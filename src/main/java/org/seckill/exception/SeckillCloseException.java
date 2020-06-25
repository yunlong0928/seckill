package org.seckill.exception;

/**
 * @author Aaron
 * @since 2020/6/23 20:16
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }
    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
