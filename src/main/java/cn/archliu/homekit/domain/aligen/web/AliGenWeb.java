package cn.archliu.homekit.domain.aligen.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.archliu.homekit.domain.aligen.service.AliGenService;
import cn.archliu.homekit.domain.aligen.web.dto.AliGenReq;
import cn.archliu.homekit.domain.aligen.web.dto.AliGenRes;
import cn.hutool.core.map.MapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = { "天猫精灵相关服务" })
@RestController
@RequestMapping("/api/hk/aligen")
public class AliGenWeb {

    @Autowired
    private AliGenService aliGenService;

    @ApiOperation("提供给天猫精灵进行语音交互")
    @PostMapping("/reply")
    public AliGenRes reply(@RequestBody AliGenReq aliGenReq) {
        log.info("aliGenReq: {}", aliGenReq);
        return AliGenRes.reply(aliGenService.reply(aliGenReq));
    }

    @ApiOperation("提供给天猫精灵进行语音交互")
    @GetMapping("/test")
    public void test() {
        aliGenService.pushNotifcation("88930", "1YUYOX7Ssq5dPJdJ", MapUtil.of("temp", "21"));
    }

}
