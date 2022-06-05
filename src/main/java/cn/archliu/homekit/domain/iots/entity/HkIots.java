package cn.archliu.homekit.domain.iots.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * iot设备表
 * </p>
 *
 * @author Arch
 * @since 2022-06-05
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("hk_iots")
public class HkIots implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * iot设置code,唯一
     */
    private String iotCode;

    /**
     * 设备名
     */
    private String iotName;

    /**
     * IPV4 地址
     */
    private String ipAddr;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 开关值,ON->开、OFF->关
     */
    private String switchValue;

    /**
     * 上一次心跳时间,默认 30 秒一次心跳
     */
    private LocalDateTime lastBeatTime;

    /**
     * 在线状态,OFFLINE->离线,ONLINE->在线
     */
    private String onlineState;

    /**
     * 创建时间
     */
    private LocalDateTime cts;

    /**
     * 更新时间
     */
    private LocalDateTime uts;

}
