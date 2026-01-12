package com.back.domain.post.post.repository;

import com.back.domain.post.post.document.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PostRepository extends ElasticsearchRepository<Post, String> {
    /*
    💡 왜 findAll()을 재선언하나요?
        - ElasticsearchRepository의 기본 findAll()은 Iterable<Post>를 반환합니다.
        - List<Post> 반환 타입으로 재선언하면 Spring Data가 자동으로 List로 변환해줍니다.
     */
    List<Post> findAll();

    Page<Post> findByTitleContaining(String title, Pageable pageable);
    Page<Post> findByContentContaining(String content, Pageable pageable);
    Page<Post> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
