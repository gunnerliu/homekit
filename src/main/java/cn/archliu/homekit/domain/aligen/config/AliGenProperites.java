package cn.archliu.homekit.domain.aligen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "archliu.aligen")
public class AliGenProperites {

    /** 天猫精灵的 accessKeyId */
    private String accessKeyId;

    /** 天猫精灵的 accessKeySecret */
    private String accessKeySecret;

    /** 天猫精灵访问的域名 */
    private String endpoint = "openapi.aligenie.com";

    /** 天猫精灵帐号 userOpenId */
    private String userOpenId;

    /** 天猫精灵设备 deviceOpenId */
    private String deviceOpenId;

}
