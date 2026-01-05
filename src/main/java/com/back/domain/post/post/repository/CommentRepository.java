package com.back.domain.post.post.repository;

import com.back.domain.post.post.document.Comment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CommentRepository extends ElasticsearchRepository<Comment, String> {
}
