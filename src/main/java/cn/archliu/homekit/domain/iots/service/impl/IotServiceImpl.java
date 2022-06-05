package cn.archliu.homekit.domain.iots.service.impl;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.archliu.homekit.common.enums.OnlineState;
import cn.archliu.homekit.domain.iots.entity.HkIots;
import cn.archliu.homekit.domain.iots.mapper.HkIotsMapper;
import cn.archliu.homekit.domain.iots.service.IotService;

@Service
public class IotServiceImpl implements IotService {

    @Autowired
    private HkIotsMapper iotsMapper;

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

}
