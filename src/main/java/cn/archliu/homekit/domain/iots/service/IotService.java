package cn.archliu.homekit.domain.iots.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.archliu.homekit.common.enums.SwitchValue;
import cn.archliu.homekit.domain.iots.entity.HkIots;

/**
 * @Author: Arch
 * @Date: 2022-03-13 22:20:33
 * @Description: iot 相关服务
 */
public interface IotService {

    /**
     * iot 设备心跳(如果不存在则先注册设备)
     * 
     * @param iotCode
     * @param ipAddr
     * @param deviceType
     * @param switchValue
     */
    void heartBeat(String iotCode, String ipAddr, String deviceType, String switchValue);

    /**
     * 分页查询所有 iot 设备
     * 
     * @param page
     * @return
     */
    IPage<HkIots> allIots(Page<HkIots> page);

    /**
     * 反转灯的开关
     * 
     * @param iotCode
     */
    void reverseLight(String iotCode);


    /**
     * 控制开关
     * 
     * @param iotCode
     * @param switchValue
     */
    void switchLight(String iotCode, SwitchValue switchValue);

}
