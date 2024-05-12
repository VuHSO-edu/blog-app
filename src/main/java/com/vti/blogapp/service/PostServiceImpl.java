package com.vti.blogapp.service;

import com.vti.blogapp.dto.PostDto;
import com.vti.blogapp.form.PostCreateForm;
import com.vti.blogapp.form.PostDeleteForm;
import com.vti.blogapp.form.PostFilterForm;
import com.vti.blogapp.form.PostUpdateForm;
import com.vti.blogapp.mapper.PostMapper;
import com.vti.blogapp.repository.PostRepository;
import com.vti.blogapp.specification.PostSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Override
    public Page<PostDto> findAll(PostFilterForm form , Pageable pageable) {
        var spec = PostSpecification.buildSpec(form);

       return postRepository.findAll(spec,pageable).map(post -> PostMapper.map(post));
        // lamda
        // method reference

    }

    @Override
    public Page<PostDto> findByTitleLikeOrContentLike(String title, String content, Pageable pageable) {
        return postRepository.findByTitleLikeOrContentLike(title, content, pageable).map(post -> PostMapper.map(post));
    }

    @Override
    public PostDto findById(Long id) {
        var post = postRepository.findById(id).get();
        return PostMapper.map(post);
    }

    @Override
    public PostDto create(PostCreateForm form) {
        var post = PostMapper.map(form);
        var savedPost = postRepository.save(post);
        return PostMapper.map(savedPost);
    }

    @Override
    public PostDto update(PostUpdateForm form, Long id) {
        var post = postRepository.findById(id).get(); // lấy đối tượng đó trong database
        PostMapper.map(form, post); // thay đổi thông tin
        var savePost = postRepository.save(post); // lưu vào trong database nhưng trả đối tượng entity Post
        return PostMapper.map(savePost); // dung map để ánh xạ Post ---> PostDto
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }


    @Override
    public void deleteByTitle(String title) {
        postRepository.deleteByTitle(title);
    }

    @Override
    @Transactional
    public void deleteByDesc(String description) {
        postRepository.deleteByDesc(description);
        // Chú ý: Khi custom query làm thay đổi dữ liệu cần thêm
        // @Modifying (Repository)
        // Transactional (Service)
    }

}
