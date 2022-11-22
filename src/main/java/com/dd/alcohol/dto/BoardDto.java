package com.dd.alcohol.dto;

import com.dd.alcohol.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String user;
    private String title;
    private String content;
//    private Long fileId;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;


    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .user(user)
                .title(title)
                .content(content)
//                .fileId(fileId)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long id, String user, String title, String content, LocalDateTime regTime, LocalDateTime updateTime) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
//        this.fileId = fileId;
        this.regTime = regTime;
        this.updateTime = updateTime;
    }
}
