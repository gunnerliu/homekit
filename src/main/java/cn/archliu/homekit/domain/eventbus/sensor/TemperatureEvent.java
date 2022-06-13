package cn.archliu.homekit.domain.eventbus.sensor;

import org.springframework.context.ApplicationEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: Arch
 * @Date: 2022-06-13 11:37:53
 * @Description: 温度传感器事件
 */
public class TemperatureEvent extends ApplicationEvent {

    public TemperatureEvent(Object source) {
        super(source);
    }

    @Data
    @Accessors(chain = true)
    public static class TemperatureEventSource {

        /** 设备编码 */
        private String iotCode;

        /** 设备名 */
        private String iotName;

        /** 温度 */
        private Double temperature;

    }

}
