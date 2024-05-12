package com.vti.blogapp.controller;

import com.vti.blogapp.dto.CommentDto;
import com.vti.blogapp.form.CommentCreateForm;
import com.vti.blogapp.form.CommentFilterForm;
import com.vti.blogapp.form.CommentUpdateForm;
import com.vti.blogapp.service.CommentService;
import com.vti.blogapp.validation.PostIdExists;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated
@RestController
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @GetMapping("/api/v1/comments")
    public Page<CommentDto> findAll(CommentFilterForm form, Pageable pageable) {
        return commentService.findAll(form,pageable);
    }

    @GetMapping("/api/v1/posts/{postId}/comments")
    public Page<CommentDto> findByPostId(@PathVariable("postId")
                                             @PostIdExists Long postId, Pageable pageable) {
        return commentService.findByPostId(postId, pageable);
    }

    @GetMapping("/api/v1/comments/body/{body}")
    public Page<CommentDto> findCommentByBody(@PathVariable String body, Pageable pageable) {
        return commentService.findCommentByBody(body, pageable);
    }

    @GetMapping("/api/v1/comments/{id}")
    public CommentDto findById(@PathVariable("id") String id) {
        return commentService.findById(id);
    }


    @PostMapping("/api/v1/posts/{postId}/comments")
    public CommentDto create(@RequestBody @Valid CommentCreateForm form, @PathVariable("postId") Long postId) {
        return commentService.create(form, postId);
    }

    @PutMapping("/api/v1/comments/{id}")
    public CommentDto update(@RequestBody @Valid CommentUpdateForm form, @PathVariable("id") String id) {
        return commentService.update(form, id);
    }

    @DeleteMapping("/api/v1/comments/{id}")
    public void delete(@PathVariable("id") String id) {
        commentService.delete(id);
    }

}
