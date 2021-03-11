package com.qf.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class QueryVo implements Serializable {
    private String title;
    private String speakerId;
    private String courseId;

    // 当前页码数
    private Integer page = 1;
    // 数据库从哪一条数据开始查
    private Integer start;
    // 每页显示数据条数
    private Integer size = 7;

    @Override
    public String toString() {
        return "QueryVo{" +
                "title='" + title + '\'' +
                ", speakerId='" + speakerId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", page=" + page +
                ", start=" + start +
                ", size=" + size +
                '}';
    }

}
