package cn.archliu.homekit.domain.aligen.web.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: Arch
 * @Date: 2022-04-19 22:20:02
 * @Description: 天猫精灵返回体
 */
@Data
@Accessors(chain = true)
public class AliGenRes {

    private String returnCode = "0";

    private String returnErrorSolution;

    private String returnMessage = "服务调用成功";

    private ReturnValue returnValue = new ReturnValue();

    @Data
    @Accessors(chain = true)
    public static class ReturnValue {

        private String reply = "server request success";

        private String resultType = "RESULT";

        private String executeCode = "SUCCESS";

    }

    public static AliGenRes reply(String content) {
        AliGenRes aliGenRes = new AliGenRes();
        aliGenRes.getReturnValue().setReply(content);
        return aliGenRes;
    }

}
