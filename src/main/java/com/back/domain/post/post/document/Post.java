package com.back.domain.post.post.document;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.OffsetDateTime;

@Document(indexName = "posts")
@Data
public class Post implements Persistable<String> {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type= FieldType.Text)
    private String content;

    @Field(type= FieldType.Keyword)
    private String author;

    @Field(
            type = FieldType.Date,
            format = DateFormat.date_time
    )
    @CreatedDate
    private OffsetDateTime createdAt;

    @Field(
            type = FieldType.Date,
            format = DateFormat.date_time
    )
    @LastModifiedDate
    private OffsetDateTime lastModifiedAt;

    public Post(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = OffsetDateTime.now();
        this.lastModifiedAt = OffsetDateTime.now();
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", createdAt=" + createdAt +
                ", lastModifiedAt=" + lastModifiedAt +
                '}';
    }

    // 영속성 컨테스트가 없으므로 isNew() 메서드로 새 문서인지 판단
    // @CreatedDate가 올바르게 설정되려면 isNew()가 적절히 구현되어야 함
    // ID가 존재하는데 새 문서일 수 있으므로(사용자 지정 ID), 날짜 필드도 함께 확인
    @Override
    public boolean isNew() {
        return id == null || (createdAt == null && lastModifiedAt == null);
    }
}
