package top.ilov.web.kan.dto.user;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String nickname;
    private String username;
    private Long exp;
    private String avatar;
    private Byte gender;
    private String introduction;
    private Long permissionGroup;
    private String ipLoc;
    private Byte userAuth;
    private String userTitle;
    private Long createdOn;

}
