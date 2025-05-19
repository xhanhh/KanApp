package top.ilov.web.kan.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ilov.web.kan.annotation.LoginRequire;
import top.ilov.web.kan.common.Result;
import top.ilov.web.kan.common.StatusCode;
import top.ilov.web.kan.common.service.JwtService;
import top.ilov.web.kan.entity.KanUser;

@Aspect
@Component
public class LoginRequireAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtService jwtService;

    @Around("@annotation(loginRequire)")
    public Object checkLogin(ProceedingJoinPoint joinPoint, LoginRequire loginRequire) throws Throwable {

        String token = request.getHeader("Authorization");
        if (token == null || !jwtService.isTokenValid(token)) {
            return Result.error(StatusCode.USER_NOT_LOGIN, "用户未登录");
        }

        KanUser user = jwtService.getUserFromToken(token);

        if (user == null) {
            return Result.error(StatusCode.TOKEN_INVALID, "用户Token无效");
        }

        if (user.getStatus() == 1) {
            return Result.error(StatusCode.USER_HAS_BEEN_BANDED, "用户已被封禁");
        }

        return joinPoint.proceed();
    }

}
