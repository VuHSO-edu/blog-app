package com.vti.blogapp.mapper;

import com.vti.blogapp.dto.PostDto;
import com.vti.blogapp.entity.Post;
import com.vti.blogapp.form.PostCreateForm;
import com.vti.blogapp.form.PostUpdateForm;

public class PostMapper {

    public static Post map(PostCreateForm form) {
       var post = new Post();
       post.setTitle(form.getTitle());
       post.setDecsription(form.getDescription());
       post.setContent(form.getContent());
       
       return post;
    }

    public static PostDto map(Post post) {
        var dto = new PostDto();

        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDecsription());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());

        return dto.withSelfRel();
    }


    public static void map(PostUpdateForm form, Post post) {
        post.setTitle(form.getTitle());
        post.setDecsription(form.getDescription());
        post.setContent(form.getContent());
    }
}
