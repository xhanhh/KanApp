package top.ilov.web.kan.service;

import top.ilov.web.kan.common.Result;
import top.ilov.web.kan.dto.user.UserCallbackDTO;
import top.ilov.web.kan.dto.user.UserLoginDTO;
import top.ilov.web.kan.dto.user.UserRegisterDTO;
import top.ilov.web.kan.entity.KanUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xhanhh
 * @since 2025-05-19
 */
public interface IKanUserService extends IService<KanUser> {

    Result<UserCallbackDTO> userRegister(UserRegisterDTO userRegisterDTO);
    Result<UserCallbackDTO> userLogin(UserLoginDTO userLoginDTO);

}
