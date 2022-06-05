package cn.archliu.homekit.common.exception;

import cn.archliu.common.exception.BaseException;

/**
 * @Author: Arch
 * @Date: 2022-04-19 21:59:43
 * @Description: 天猫精灵异常
 */
public class AliGenException extends BaseException {

    public AliGenException() {
        super("ali gen exception !");
    }

    public AliGenException(String message) {
        super(message);
    }

    public static AliGenException throwE() {
        return new AliGenException();
    }

    public static AliGenException throwE(String message) {
        return new AliGenException(message);
    }

}
