package cn.archliu.homekit.domain.aligen.service;

import java.util.Map;

import cn.archliu.homekit.domain.aligen.web.dto.AliGenReq;

/**
 * @Author: Arch
 * @Date: 2022-04-20 22:03:25
 * @Description: 天猫精灵语音交互服务
 */
public interface AliGenService {

    /**
     * 提供给天猫精灵进行语音交互
     * 
     * @param aliGenReq 语音播放的文字
     * @return
     */
    String reply(AliGenReq aliGenReq);

    /**
     * 向天猫精灵推送消息
     * 
     * @param skillId           天猫精灵技能 ID
     * @param messageTemplateId 天猫精灵消息模板 ID
     * @param placeHolder       天猫精灵消息模板中的参数
     */
    void pushNotifcation(String skillId, String messageTemplateId, Map<String, String> placeHolder);

}
