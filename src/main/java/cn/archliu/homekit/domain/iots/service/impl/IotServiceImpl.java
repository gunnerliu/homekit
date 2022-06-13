package cn.archliu.homekit.domain.iots.service.impl;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.archliu.common.exception.sub.ParamErrorException;
import cn.archliu.homekit.common.enums.OnlineState;
import cn.archliu.homekit.common.enums.SwitchValue;
import cn.archliu.homekit.domain.iots.entity.HkIots;
import cn.archliu.homekit.domain.iots.enums.DeviceType;
import cn.archliu.homekit.domain.iots.mapper.HkIotsMapper;
import cn.archliu.homekit.domain.iots.service.IotService;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IotServiceImpl implements IotService {

    @Autowired
    private HkIotsMapper iotsMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void heartBeat(String iotCode, String ipAddr, String deviceType, String switchValue) {
        HkIots one = new LambdaQueryChainWrapper<>(iotsMapper).eq(HkIots::getIotCode, iotCode).one();
        if (one != null) { // 心跳
            new LambdaUpdateChainWrapper<>(iotsMapper).set(HkIots::getLastBeatTime, LocalDateTime.now())
                    .set(HkIots::getOnlineState, OnlineState.ONLINE.name()).set(HkIots::getSwitchValue, switchValue)
                    .eq(HkIots::getId, one.getId()).update();
            if (OnlineState.OFFLINE.name().equals(one.getOnlineState())) {
                // TODO 通知设备上线
            }
        } else { // 新增设备
            HkIots newIot = new HkIots().setIotCode(iotCode).setIpAddr(ipAddr).setDeviceType(deviceType)
                    .setOnlineState(OnlineState.ONLINE.name()).setSwitchValue(switchValue)
                    .setLastBeatTime(LocalDateTime.now());
            iotsMapper.insert(newIot);
        }
    }

    @Override
    public IPage<HkIots> allIots(Page<HkIots> page) {
        return iotsMapper.selectPage(page, null);
    }

    @Override
    public synchronized void reverseLight(String iotCode) {
        // 1、查看该设备是否是灯
        HkIots light = new LambdaQueryChainWrapper<>(iotsMapper).eq(HkIots::getIotCode, iotCode).one();
        if (light == null || !DeviceType.LIGHT.name().equals(light.getDeviceType())
                || StrUtil.isBlank(light.getIpAddr())) {
            log.warn("{} 设备不存在或不是灯或者IP地址不存在!", iotCode);
            throw ParamErrorException.throwE("设备不存在或不是灯！");
        }
        String lightSwitch = "1";
        // 2、查看灯的设备值
        if (SwitchValue.ON.name().equals(light.getSwitchValue())) {
            // 开灯
            lightSwitch = "0";
        }
        // 发送指令
        sendLightCmd(light.getIpAddr(), lightSwitch);
    }

    @Override
    public synchronized void switchLight(String iotCode, SwitchValue switchValue) {
        if (switchValue == null) {
            throw ParamErrorException.throwE("switchValue 不允许为空！");
        }
        // 1、查看该设备是否是灯
        HkIots light = new LambdaQueryChainWrapper<>(iotsMapper).eq(HkIots::getIotCode, iotCode).one();
        if (light == null || !DeviceType.LIGHT.name().equals(light.getDeviceType())
                || StrUtil.isBlank(light.getIpAddr())) {
            log.warn("{} 设备不存在或不是灯或者IP地址不存在!", iotCode);
            throw ParamErrorException.throwE("设备不存在或不是灯！");
        }
        // 发送指令
        sendLightCmd(light.getIpAddr(), light.getSwitchValue());
    }

    /**
     * 发送指令
     * 
     * @param ipAddr
     * @param switchValue
     */
    private void sendLightCmd(String ipAddr, String switchValue) {
        String url = new StringBuilder().append("http://").append(ipAddr).append("/gpio/").append(switchValue)
                .toString();
        // 发送请求
        restTemplate.getForEntity(url, String.class);
    }

}
