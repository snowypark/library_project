package com.study.library.dto;

import com.study.library.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SignupReqDto {

    private BCryptPasswordEncoder passwordEncoder;

    @Pattern(regexp = "^[a-z]{1}[a-z0-9]{2,10}+$", message = "영문 숫자 조합 6~10자리여야 합니다")
    private String username;
    @Pattern(regexp = "^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{7,128}+$", message = "대소문자, 숫자, 특수문자 조합으로 8 ~ 128자리여야 합니다")
    private String password;
    @Pattern(regexp = "^[ㄱ-ㅎ|가-힣]{1,}+$", message = "한글이여야 합니다.")
    private String name;
    @Email(regexp = "^[a-zA-Z0-9]+@[0-9a-zA-Z]+\\.[a-z]+$")
    private String email;

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .build();
    }
}
