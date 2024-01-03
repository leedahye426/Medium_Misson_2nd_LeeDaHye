package com.ll.medium_clone_coding.domain.post.post.service;

import com.ll.medium_clone_coding.domain.member.member.entity.Member;
import com.ll.medium_clone_coding.domain.post.post.entity.Post;
import com.ll.medium_clone_coding.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    public List<Post> getList() {
        return postRepository.findTop30ByIsPublishedTrueOrderByCreateDateDesc();
    }

    @Transactional
    public Post write(Member author, String title, String body, String isPublished, String isPaid) {
        Post post = Post.builder()
                .author(author)
                .title(title)
                .body(body)
                .isPublished(Boolean.parseBoolean(isPublished))
                .isPaid(Boolean.parseBoolean(isPaid))
                .build();

        postRepository.save(post);

        return post;
    }

    public Page<Post> getList(int page) {
        Pageable pageable = PageRequest.of(page, 9);
        return postRepository.findByIsPublishedTrueOrderByCreateDateDesc(pageable);
    }

    public Post getPost(long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()) throw new RuntimeException("entity not found");
        return post.get();
    }

    public Post getPost(String username, long id) {
        Optional<Post> post = postRepository.findByAuthorUsernameAndId(username, id);
        if(post.isEmpty()) throw new RuntimeException("entity not found");

        return post.get();
    }

    @Transactional
    public void modify(Post post, String title, String body, String isPublished, String isPaid) {
        post.setTitle(title);
        post.setBody(body);
        post.setPublished(Boolean.parseBoolean(isPublished));
        post.setPaid(Boolean.parseBoolean(isPaid));
    }

    @Transactional
    public void delete(Post post) {
        postRepository.delete(post);
    }
}
