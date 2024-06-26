package com.vti.blogapp.dto;

import com.vti.blogapp.controller.PostController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class PostDto extends RepresentationModel<PostDto> {

    private Long id;
    private String title;
    private String description;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostDto withSelfRel() {
        var controller = methodOn(PostController.class);
        var dto = controller.fingById(id);
        var link = linkTo(dto).withSelfRel();
        return add(link);
    }

}
