package cn.archliu.homekit.domain.iots.enums;

import cn.archliu.common.exception.sub.ParamErrorException;

/**
 * @Author: Arch
 * @Date: 2022-04-17 19:45:44
 * @Description: 设备类型
 */
public enum DeviceType {

    LIGHT, // 灯
    UNKNOW;

    public static DeviceType convert(String deviceType) {
        for (DeviceType item : values()) {
            if (item.name().equals(deviceType)) {
                return item;
            }
        }
        return UNKNOW;
    }

    public static DeviceType convertThrowE(String deviceType) {
        for (DeviceType item : values()) {
            if (item.name().equals(deviceType)) {
                return item;
            }
        }
        throw ParamErrorException.throwE("设备类型错误！");
    }

}
