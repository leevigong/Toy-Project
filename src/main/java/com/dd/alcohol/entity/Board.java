package com.dd.alcohol.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="board")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @Id
    @Column(name="board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // 게시글 코드

    @Column(nullable = false, length = 500)
    private String title; // 제목

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 내용

    @Column(nullable = false)
    private String user; // 작성자

    private String view_cnt; // 조회수

//    @Column
//    private Long fileId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regTime; // 동록 시간

    @LastModifiedDate
    private LocalDateTime updateTime; // 수정 시간

    @Builder
    public Board(Long id, String title, String content, String user){
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
//        this.fileId = fileId;
    }


}
