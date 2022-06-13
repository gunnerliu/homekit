package cn.archliu.homekit.domain.iots.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.archliu.common.response.ComRes;
import cn.archliu.common.response.sub.CRUDData;
import cn.archliu.common.response.sub.ResData;
import cn.archliu.homekit.common.util.PageUtil;
import cn.archliu.homekit.domain.iots.entity.HkIots;
import cn.archliu.homekit.domain.iots.service.IotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: Arch
 * @Date: 2022-03-13 21:54:59
 * @Description: iot设备接口
 */
@Api(tags = { "iot设备相关接口" })
@RestController
@RequestMapping("/api/hk/iots")
public class IotsWeb {

    @Autowired
    private IotService iotService;

    @ApiOperation("设备信息")
    @GetMapping("/allIots")
    public ComRes<CRUDData<HkIots>> allIots(@RequestParam("pageIndex") Integer pageIndex,
            @RequestParam("pageSize") Integer pageSize) {
        Page<HkIots> page = PageUtil.build(pageIndex, pageSize);
        IPage<HkIots> data = iotService.allIots(page);
        return ComRes.success(new CRUDData<HkIots>().setItems(data.getRecords()).setTotal(data.getTotal()));
    }

    @ApiOperation("iot 设备心跳(如果不存在则先注册设备)")
    @GetMapping("/heartBeat")
    public ComRes<ResData<Void>> heartBeat(@RequestParam("iotCode") String iotCode,
            @RequestParam("ipAddr") String ipAddr,
            @RequestParam("deviceType") String deviceType, @RequestParam("switchValue") String switchValue) {
        iotService.heartBeat(iotCode, ipAddr, deviceType, switchValue);
        return ComRes.success();
    }

    @ApiOperation("反转灯的开关")
    @GetMapping("/switchLight")
    public ComRes<ResData<Void>> switchLight(@RequestParam("iotCode") String iotCode) {
        iotService.reverseLight(iotCode);
        return ComRes.success();
    }

}
