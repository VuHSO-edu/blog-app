package com.vti.blogapp.controller;


import com.vti.blogapp.dto.PostDto;
import com.vti.blogapp.form.PostCreateForm;
import com.vti.blogapp.form.PostDeleteForm;
import com.vti.blogapp.form.PostFilterForm;
import com.vti.blogapp.form.PostUpdateForm;
import com.vti.blogapp.service.PostService;
import com.vti.blogapp.validation.PostIdExists;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController // @Controller + @RestponseBody (trả về dữ liệu)
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @GetMapping("/api/v1/posts")
    public Page<PostDto> findAll(PostFilterForm form , Pageable pageable) {
        return postService.findAll(form, pageable);
    }

    @GetMapping("/api/v1/posts/title/{title}/content/{content}")
    public Page<PostDto> findByTitleLikeOrContentLike(
           @PathVariable("title") String title,
           @PathVariable("content") String content,
            Pageable pageable) {
        return postService.findByTitleLikeOrContentLike("%" + title + "%","%" + content + "%", pageable);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostDto fingById( @PathVariable("id")
                                 @PostIdExists Long id) {
        return postService.findById(id);
    }
    // http://com.vti/api/v1/posts + HTTP method

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/posts")
    public PostDto create(@Valid @RequestBody PostCreateForm form) {
        return postService.create(form);
    }

    @PutMapping("/api/v1/posts/{id}")
    public PostDto update(@RequestBody @Valid PostUpdateForm form,@PathVariable("id") @PostIdExists Long id) {
            return postService.update(form,id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public void deleteById(@PathVariable("id")
                               @PostIdExists Long id) {
        postService.deleteById(id);
    }

    @DeleteMapping("/api/v1/posts")
    public void deleteByTitle(@RequestBody  String title) {
        postService.deleteByTitle(title);
    }

    @DeleteMapping("/api/v1/posts/desc/{description}")
    public void deleteByDesc(@PathVariable("description") String description) {
        postService.deleteByDesc(description);
    }
}
