package cn.archliu.homekit.domain.eventbus.sensor;

import org.springframework.context.ApplicationEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: Arch
 * @Date: 2022-06-13 15:45:00
 * @Description: 湿度传感器事件
 */
public class HumidityEvent extends ApplicationEvent {

    public HumidityEvent(Object source) {
        super(source);
    }

    @Data
    @Accessors(chain = true)
    public static class HumidityEventSource {

        /** 设备编码 */
        private String iotCode;

        /** 设备名 */
        private String iotName;

        /** 湿度 */
        private Double humidity;

    }

}
