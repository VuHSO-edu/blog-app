package com.vti.blogapp.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostCreateForm {

    @NotBlank(message = "{post.title.NotBlank.message}")
    @Length(max = 50, message = "{post.title.Length.message}")
    private String title;

    @NotBlank(message = "Mô tả bài viết không được để trống")
    @Length(max = 255, message = "Mô tả bài viết không được quá 255 ký tự")
    private String description;

    @NotBlank(message = "Nội dung bài viết không được để trống")
    @Length(max = 150, message = "Nội dung bài viết không được quá 150 ký tự")
    private String content;
}
