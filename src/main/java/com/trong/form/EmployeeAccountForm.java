package com.trong.form;

import com.trong.validation.annotation.FieldMatch;
import com.trong.validation.annotation.UniqueEmail;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@FieldMatch(first = "password", second = "passwordConfirm", message = "Hai mật khẩu không giống nhau")
public class EmployeeAccountForm {
    @NotEmpty(message = "Xin hãy nhập tên")
    private String name;
    @NotEmpty(message = "Xin hãy nhập email")
    @UniqueEmail
    private String email;
    @NotEmpty(message = "Xin hãy nhập mật khẩu")
    @Length(min = 6, message = "Độ dài tối thiểu là 6 ký tự")
    private String password;
    @NotEmpty(message = "Xin hãy nhập lại mật khẩu ở trên")
    private String passwordConfirm;
}
