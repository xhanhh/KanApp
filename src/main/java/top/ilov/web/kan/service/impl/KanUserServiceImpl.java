package top.ilov.web.kan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.ilov.web.kan.common.Result;
import top.ilov.web.kan.common.StatusCode;
import top.ilov.web.kan.common.TimeProvider;
import top.ilov.web.kan.common.service.DTOService;
import top.ilov.web.kan.common.service.JwtService;
import top.ilov.web.kan.dto.user.UserCallbackDTO;
import top.ilov.web.kan.dto.user.UserLoginDTO;
import top.ilov.web.kan.dto.user.UserRegisterDTO;
import top.ilov.web.kan.entity.KanUser;
import top.ilov.web.kan.mapper.KanUserMapper;
import top.ilov.web.kan.service.IKanUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ilov.web.kan.util.MD5Utils;

import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xhanhh
 * @since 2025-05-19
 */
@Service
public class KanUserServiceImpl extends ServiceImpl<KanUserMapper, KanUser> implements IKanUserService, TimeProvider {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private DTOService dtoService;

    @Override
    public Result<UserCallbackDTO> userRegister(UserRegisterDTO userRegisterDTO) {

        if (StringUtils.isBlank(userRegisterDTO.getUsername()) || StringUtils.isBlank(userRegisterDTO.getPassword())) {
            return Result.error(StatusCode.EMPTY_BODY, "用户名或密码为空");
        }

        KanUser existingUser = this.getOne(new LambdaQueryWrapper<KanUser>()
                .eq(KanUser::getUsername, userRegisterDTO.getUsername())
                .eq(KanUser::getIsDel, "0"));

        if (existingUser != null) {
            return Result.error(StatusCode.USERNAME_HAS_BEEN_USED, "该用户名已被占用");
        }

        String pw = MD5Utils.encrypt(userRegisterDTO.getPassword());

        if (!pw.equals(MD5Utils.encrypt(userRegisterDTO.getConfirmPassword()))) {
            return Result.error(StatusCode.CONTENT_ERROR, "两次输入的密码不一致");
        }

        KanUser user = new KanUser();
        user.setNickname("用户" + UUID.randomUUID().toString().replace("-", "").substring(0, 6));
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(pw);
        user.setIp("");
        user.setIpLoc("");
        user.setCreatedOn(getCurrentTime());
        user.setModifiedOn(getCurrentTime());
        user.setAvatar("https://cn.cravatar.com/avatar/" + user.getUsername() + "?d=identicon");
        this.save(user);

        KanUser newUser = this.getById(user.getId());

        String token = jwtService.generateToken(user.getUsername(), newUser.getId());
        return Result.success(dtoService.compUserCallbackDTO(token, dtoService.userToUserDTO(newUser)));

    }

    @Override
    public Result<UserCallbackDTO> userLogin(UserLoginDTO userLoginDTO) {

        if (StringUtils.isBlank(userLoginDTO.getUsername()) || StringUtils.isBlank(userLoginDTO.getPassword())) {
            return Result.error(StatusCode.EMPTY_BODY, "请输入用户名和密码");
        }

        KanUser user = this.getOne(new LambdaQueryWrapper<KanUser>()
                .eq(KanUser::getUsername, userLoginDTO.getUsername())
                .eq(KanUser::getIsDel, "0"));

        if (user == null || !user.getPassword().equals(MD5Utils.encrypt(userLoginDTO.getPassword()))) {
            return Result.error(StatusCode.USERNAME_OR_PW_ERROR, "用户名或密码错误");
        }

        user.setModifiedOn(getCurrentTime());
        user.setIp("");
        user.setIpLoc("");
        this.updateById(user);

        String token = jwtService.generateToken(user.getUsername(), user.getId());
        return Result.success(dtoService.compUserCallbackDTO(token, dtoService.userToUserDTO(user)));

    }

}
