package com.vti.blogapp.mapper;

import com.vti.blogapp.dto.UserDto;
import com.vti.blogapp.entity.User;
import com.vti.blogapp.form.UserCreateForm;

public class UserMapper {

    public static User map(UserCreateForm form) {
        var user = new User();
        user.setName(form.getName());
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        return user;
    }

    public static UserDto map(User user) {
        var userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        return userDto;
    }
}
