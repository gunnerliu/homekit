package cn.archliu.homekit.domain.eventbus.light;

import org.springframework.context.ApplicationEvent;

import cn.archliu.homekit.common.enums.SwitchValue;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: Arch
 * @Date: 2022-06-13 11:18:10
 * @Description: 灯状态事件
 */
public class LightEvent extends ApplicationEvent {

    public LightEvent(LightEventSource source) {
        super(source);
    }

    public LightEventSource getLightEventSource() {
        return (LightEventSource) getSource();
    }

    @Data
    @Accessors(chain = true)
    public static class LightEventSource {

        /** 设备编码 */
        private String iotCode;

        /** 设备名 */
        private String iotName;

        /** 开关值,ON->开、OFF->关 */
        private SwitchValue switchValue;

    }

}
