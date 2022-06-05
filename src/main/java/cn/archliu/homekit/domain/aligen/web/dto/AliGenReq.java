package cn.archliu.homekit.domain.aligen.web.dto;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: Arch
 * @Date: 2022-04-20 21:12:14
 * @Description: 天猫精灵语音请求体
 */
@Data
@Accessors(chain = true)
public class AliGenReq {

    /** 系统级别的会话ID，用户不重启设备则此会话ID不会改变 */
    private String sessionId;

    /** 进入意图时用户所说的语句 */
    private String utterance;

    /** 账号关联的token信息 */
    private String token;

    /** 拓展信息，目前在线测试 requestData 字段为空 */
    private RequestData requestData;

    /** 挂载技能或应用的集合ID */
    private Long botId;

    /** 领域ID，领域是技能的语音交互模型和后端服务组合在一起形成的。一个技能至少拥有一个测试领域。技能上线后，还将拥有一个线上领域。 */
    private Long domainId;

    /** 技能ID */
    private Long skillId;

    /** 技能名称 */
    private String skillName;

    /** 意图ID */
    private Long intentId;

    /** 意图标识 */
    private String intentName;

    /** 从用户语句中抽取出的 slot 参数信息 */
    private List<SlotEntity> slotEntities;

    /** 本次请求的ID */
    private String requestId;

    /** 在线测试 device 字段为空,用户的设备信息 */
    private Device device;

    /** 技能粒度的session信息 */
    private SkillSession skillSession;

    @Data
    @Accessors(chain = true)
    public static class RequestData {

        /** 在当前技能中唯一识别用户 */
        private String userOpenId;

        /** 跨技能设备统一标识 */
        private String deviceUnionIds;

        /** 跨技能用户统一标识 */
        private String userUnionIds;
    }

    @Data
    @Accessors(chain = true)
    public static class SlotEntity {

        /** 意图参数ID */
        private Long intentParameterId;

        /** 意图中定义的参数名称 */
        private String intentParameterName;

        /** 原始句子中抽取出来的未做处理的 slot 值 */
        private String originalValue;

        /** slot 归一化后的值 */
        private String standardValue;

        /** 该 slot 已存在的会话轮数 */
        private Long liveTime;

        /** 参数创建的时间戳 */
        private Long createTimeStamp;

        private String slotName;

        /** 使用这个值 */
        private String slotValue;

    }

    @Data
    @Accessors(chain = true)
    public static class Device {

        private String deviceOpenId;

    }

    @Data
    @Accessors(chain = true)
    public static class SkillSession {

        /** 当前会话的sessionId */
        private String skillSessionId;

        /** 当前会话是否为新会话 */
        private Boolean newSession;

    }

}
