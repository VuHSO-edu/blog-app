package com.vti.blogapp.form;

import com.vti.blogapp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    private String name;
    private String username;
    private String email;
    private String password;
}
