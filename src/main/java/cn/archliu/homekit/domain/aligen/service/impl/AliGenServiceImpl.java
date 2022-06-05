package cn.archliu.homekit.domain.aligen.service.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.aliyun.aligenieiap_1_0.Client;
import com.aliyun.aligenieiap_1_0.models.PushNotificationsRequest;
import com.aliyun.aligenieiap_1_0.models.PushNotificationsRequest.PushNotificationsRequestNotificationUnicastRequest;
import com.aliyun.aligenieiap_1_0.models.PushNotificationsRequest.PushNotificationsRequestNotificationUnicastRequestSendTarget;
import com.aliyun.aligenieiap_1_0.models.PushNotificationsRequest.PushNotificationsRequestTenantInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.archliu.homekit.common.exception.AliGenException;
import cn.archliu.homekit.domain.aligen.config.AliGenProperites;
import cn.archliu.homekit.domain.aligen.manager.AliGenClientManager;
import cn.archliu.homekit.domain.aligen.service.AliGenService;
import cn.archliu.homekit.domain.aligen.web.dto.AliGenReq;
import cn.archliu.homekit.domain.aligen.web.dto.AliGenReq.SlotEntity;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AliGenServiceImpl implements AliGenService {

    @Autowired
    private AliGenProperites aliGenProperites;

    @Autowired
    private AliGenClientManager aliGenClientManager;

    @Override
    public String reply(AliGenReq aliGenReq) {
        if (aliGenReq == null || CharSequenceUtil.isBlank(aliGenReq.getSkillName())) {
            return "抱歉，没有匹配到技能";
        }
        if ("有话说".equals(aliGenReq.getSkillName())) {
            return toTalk(aliGenReq);
        }
        return "抱歉，没有匹配到技能";
    }

    @Override
    public void pushNotifcation(String skillId, String messageTemplateId, Map<String, String> placeHolder) {
        PushNotificationsRequestNotificationUnicastRequest unicastRequest = new PushNotificationsRequestNotificationUnicastRequest();
        PushNotificationsRequest request = new PushNotificationsRequest();
        request.setTenantInfo(new PushNotificationsRequestTenantInfo());
        request.setNotificationUnicastRequest(unicastRequest);
        unicastRequest.setMessageTemplateId(messageTemplateId);
        PushNotificationsRequestNotificationUnicastRequestSendTarget sendTarget = new PushNotificationsRequestNotificationUnicastRequestSendTarget();
        unicastRequest.setSendTarget(sendTarget);
        sendTarget.setTargetType("DEVICE_OPEN_ID");
        sendTarget.setTargetIdentity(aliGenProperites.getDeviceOpenId());
        unicastRequest.setPlaceHolder(placeHolder);
        unicastRequest.setEncodeType("SKILL_ID");
        unicastRequest.setEncodeKey(skillId);
        unicastRequest.setIsDebug(true);
        Client client = aliGenClientManager.createClient();
        try {
            client.pushNotifications(request);
        } catch (Exception e) {
            log.error("天猫精灵推送消息异常！", e);
            throw AliGenException.throwE("推送天猫精灵消息异常！");
        }
    }

    /**
     * 有话说
     * 
     * @param aliGenReq
     * @return
     */
    private String toTalk(AliGenReq aliGenReq) {
        if (CollUtil.isEmpty(aliGenReq.getSlotEntities())) {
            return "hi";
        }
        Set<String> keys = new HashSet<>();
        for (SlotEntity slotEntity : aliGenReq.getSlotEntities()) {
            if (CharSequenceUtil.isNotBlank(slotEntity.getSlotValue())) {
                keys.add(slotEntity.getSlotValue());
            }
        }
        if (keys.contains("说说")) {
            return "今天还需要加油哦！";
        }
        return "hi";
    }

}
