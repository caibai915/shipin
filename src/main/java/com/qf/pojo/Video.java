package com.qf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Video {
    private Integer id;

    private String title;

    private Integer time;

    private Integer speakerId;

    private Integer courseId;

    private String videoUrl;

    private String imageUrl;

    private Integer playNum;

    private String detail;

    private Speaker speaker;


}