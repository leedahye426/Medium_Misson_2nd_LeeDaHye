package com.ll.medium_clone_coding.domain.post.post.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {
    @NotBlank(message = "제목은 필수항목입니다.")
    private String title;

    @NotBlank(message = "내용은 필수항목입니다.")
    private String body;

    private String isPublished;

    private String isPaid;
}
