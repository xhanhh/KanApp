package top.ilov.web.kan.error;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ilov.web.kan.common.Result;
import top.ilov.web.kan.common.StatusCode;

@RestController
@RequestMapping("/error")
public class ErrorController {

    @GetMapping(value = "/404")
    public Result<String> code404() {
        return Result.error(StatusCode.NOT_FOUND, "【Error Code: 404/Not Found】当前服务器状态正常，您访问的内容不存在。");
    }

    @GetMapping(value = "/500")
    public Result<String> code500() {
        return Result.error(StatusCode.INTERNAL_ERROR, "error/500");
    }

}
