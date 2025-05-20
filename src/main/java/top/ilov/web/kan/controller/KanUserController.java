package top.ilov.web.kan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ilov.web.kan.common.Result;
import top.ilov.web.kan.dto.user.UserCallbackDTO;
import top.ilov.web.kan.dto.user.UserLoginDTO;
import top.ilov.web.kan.dto.user.UserRegisterDTO;
import top.ilov.web.kan.service.IKanUserService;

/**
 * 用户 前端控制器
 *
 * @author xhanhh
 * @since 2025-05-19
 */
@RestController
@RequestMapping("/v1/user")
public class KanUserController {

    @Autowired
    private IKanUserService userService;

    @PostMapping("/register")
    public Result<UserCallbackDTO> userRegister(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.userRegister(userRegisterDTO);
    }

    @PostMapping("/login")
    public Result<UserCallbackDTO> userLogin(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.userLogin(userLoginDTO);
    }

}
