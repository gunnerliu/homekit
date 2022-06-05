package cn.archliu.homekit.domain.aligen.manager;

import com.aliyun.aligenieiap_1_0.Client;
import com.aliyun.teaopenapi.models.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.archliu.homekit.common.exception.AliGenException;
import cn.archliu.homekit.domain.aligen.config.AliGenProperites;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Arch
 * @Date: 2022-04-19 21:53:38
 * @Description: 天猫精灵客户端管理
 */
@Slf4j
@Component
public class AliGenClientManager {

    @Autowired
    private AliGenProperites aliGenProperites;

    /**
     * 使用AK&SK初始化账号Client
     * 
     * @return
     */
    public Client createClient() {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(aliGenProperites.getAccessKeyId())
                // 您的AccessKey Secret
                .setAccessKeySecret(aliGenProperites.getAccessKeySecret());
        // 访问的域名
        config.endpoint = aliGenProperites.getEndpoint();
        try {
            return new Client(config);
        } catch (Exception e) {
            log.error("天猫精灵连接异常！", e);
            throw AliGenException.throwE("天猫精灵连接异常！");
        }
    }

}
