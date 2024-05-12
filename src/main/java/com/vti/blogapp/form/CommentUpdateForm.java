package com.vti.blogapp.form;

import com.vti.blogapp.entity.Comment;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CommentUpdateForm {
    @NotBlank(message = "Tên không được để trống")
    @Length(max = 50, message = "Tên không được quá 50 ký tự")
    private String name;

    @NotBlank(message = "Email không được để trống")
    @Length(max = 75, message = "Email không được quá 75 ký tự")
    @Email
    private String email;

    @NotBlank(message = "Nội dung không được để trống")
    @Length(max = 100, message = "Nội dung không được quá 100 ký tự")
    private String body;

//    @NotNull(message = "Trạng thái không được để trống")
    @Pattern(regexp = "REVIEW|OPEN|CLOSE", message = "Trạng thái không hợp lệ(REVIEW,OPEN,CLOSE)")
    private String status;
}
