package com.ll.medium_clone_coding.domain.post.post.repository;

import com.ll.medium_clone_coding.domain.post.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByIsPublishedTrueOrderByCreateDateDesc(Pageable pageable);

    Optional<Post> findByAuthorUsernameAndId(String username, long id);

    List<Post> findTop30ByIsPublishedTrueOrderByCreateDateDesc();
}
