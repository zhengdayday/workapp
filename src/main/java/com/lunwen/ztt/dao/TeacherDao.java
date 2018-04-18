package com.lunwen.ztt.dao;

import com.lunwen.ztt.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-18
 * Time: 下午6:50
 */
public interface TeacherDao extends JpaRepository<Teacher, Long> {
}
