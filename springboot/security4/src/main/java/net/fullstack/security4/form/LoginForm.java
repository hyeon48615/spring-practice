package net.fullstack.security4.form;

import lombok.Data;

@Data
public class LoginForm {
    //사용명
    private String usernameInput;
    //비밀번호
    private String passwordInput;
}