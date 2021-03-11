package com.qf.service;

import com.qf.pojo.Course;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {
    List<Course> findAll();


    Course selectCourseById(int id);

}
