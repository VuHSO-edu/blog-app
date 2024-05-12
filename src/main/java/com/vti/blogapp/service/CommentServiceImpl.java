package com.vti.blogapp.service;

import com.vti.blogapp.dto.CommentDto;
import com.vti.blogapp.form.CommentCreateForm;
import com.vti.blogapp.form.CommentFilterForm;
import com.vti.blogapp.form.CommentUpdateForm;
import com.vti.blogapp.mapper.CommentMapper;
import com.vti.blogapp.repository.CommentRepository;
import com.vti.blogapp.repository.PostRepository;
import com.vti.blogapp.specification.CommentSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Override
    public Page<CommentDto> findAll(CommentFilterForm form, Pageable pageable) {
        var spec = CommentSpecification.buildSpec(form);
        return commentRepository.findAll(spec,pageable).map(CommentMapper::map);
    }

    @Override
    public Page<CommentDto> findByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable).map(CommentMapper::map);
    }

    @Override
    public Page<CommentDto> findCommentByBody(String body, Pageable pageable) {
        return commentRepository.findCommentByBody(body, pageable).map(CommentMapper::map);
    }

    @Override
    public CommentDto findById(String id) {
        var comment = commentRepository.findById(id).get();
        return CommentMapper.map(comment);
    }

    @Override
    public CommentDto create(CommentCreateForm form , Long postId) {
        var comment = CommentMapper.map(form);
        var post = postRepository.findById(postId).get();
        comment.setPost(post);
        var savedComment = commentRepository.save(comment);
        return CommentMapper.map(savedComment);
    }

    @Override
    public CommentDto update(CommentUpdateForm form, String id) {
        var comment = commentRepository.findById(id).get();
        CommentMapper.map(form, comment);
        var saveComment = commentRepository.save(comment);
        return CommentMapper.map(saveComment);

    }

    @Override
    public void delete(String id) {
        commentRepository.deleteById(id);
    }
}
