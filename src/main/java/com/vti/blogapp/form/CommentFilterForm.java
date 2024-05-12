package com.vti.blogapp.form;

import com.vti.blogapp.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentFilterForm {
    private String search;
    private Long postId;
    private Comment.Status status;


}
