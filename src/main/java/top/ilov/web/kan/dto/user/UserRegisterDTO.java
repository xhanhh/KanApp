package top.ilov.web.kan.dto.user;

import lombok.Data;

@Data
public class UserRegisterDTO {

    private String username;
    private String password;
    private String confirmPassword;

}
