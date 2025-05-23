package top.ilov.web.kan.common;

public class StatusCode {

    public static final int EMPTY_BODY = 10000; // 提交的内容为空
    public static final int CONTENT_ERROR = 10001; // 提交的内容有误

    public static final int USER_NOT_LOGIN = 10100; // 用户未登录
    public static final int TOKEN_INVALID = 10101; // 用户token无效
    public static final int USER_HAS_BEEN_BANDED = 10102; // 用户被封禁
    public static final int USERNAME_HAS_BEEN_USED = 10103; // 用户名被占用
    public static final int USERNAME_OR_PW_ERROR = 10104; // 用户名或密码错误

    public static final int INTERNAL_ERROR = 10500; // 服务器内部错误
    public static final int NOT_FOUND = 10404; //访问的内容未找到
    public static final int OK = 10200;

}
