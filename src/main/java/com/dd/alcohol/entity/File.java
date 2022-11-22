package com.dd.alcohol.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File{

    @Id
    @GeneratedValue
    private Long id;


    private String origFilename;


    private String filename;


    private String filePath;

    @Builder
    public File(Long id, String origFilename, String filename, String filePath) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }



}
