package top.ilov.web.kan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xhanhh
 * @since 2025-05-19
 */
@Getter
@Setter
@ToString
@TableName("kan_user")
public class KanUser {

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户经验
     */
    private Long exp;

    /**
     * 用户状态：0正常，1封禁
     */
    private Byte status;

    /**
     * 头像链接
     */
    private String avatar;

    /**
     * 用户性别：0不显示，1男，2女，3...
     */
    private Byte gender;

    /**
     * 用户简介内容
     */
    private String introduction;

    /**
     * 用户所属权限组，0：普通用户权限
     */
    private Long permissionGroup;

    /**
     * 用户ip
     */
    private String ip;

    /**
     * ip地点
     */
    private String ipLoc;

    /**
     * 用户认证类型：0未认证，1程序官方认证，2其他官方认证
     */
    private Byte userAuth;

    /**
     * 用户认证信息
     */
    private String userTitle;

    /**
     * 创建时间
     */
    private Long createdOn;

    /**
     * 修改时间
     */
    private Long modifiedOn;

    /**
     * 删除时间
     */
    private Long deletedOn;

    /**
     * 是否删除，0否，1是
     */
    private Byte isDel;
}
