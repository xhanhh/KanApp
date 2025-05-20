package top.ilov.web.kan.dto.user;

import lombok.Data;

@Data
public class UserCallbackDTO {

    private UserDTO userInfo;
    private String token;

}
