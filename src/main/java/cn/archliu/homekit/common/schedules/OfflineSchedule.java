package cn.archliu.homekit.common.schedules;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.archliu.homekit.common.enums.OnlineState;
import cn.archliu.homekit.domain.iots.entity.HkIots;
import cn.archliu.homekit.domain.iots.mapper.HkIotsMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Arch
 * @Date: 2022-03-13 22:42:11
 * @Description: 扫描下线设备
 */
@Slf4j
@Component
public class OfflineSchedule {

    @Autowired
    private HkIotsMapper iotsMapper;

    /**
     * 扫描下线设备
     */
    public void offlineIots() {
        // 捞出 3 分钟之内不存在心跳的在线设备
        List<HkIots> iots = new LambdaQueryChainWrapper<>(iotsMapper)
                .eq(HkIots::getOnlineState, OnlineState.ONLINE.name())
                .apply("last_beat_time", " DATE_SUB(NOW(), INTERVAL 3 MINUTE) ").list();
        if (iots.isEmpty()) {
            log.debug("不存在下线设备！");
            return;
        }
        new LambdaUpdateChainWrapper<>(iotsMapper).set(HkIots::getOnlineState, OnlineState.OFFLINE.name())
                .in(HkIots::getId, iots.stream().map(HkIots::getId).collect(Collectors.toList())).update();
        // TODO 下线通知

    }

}
