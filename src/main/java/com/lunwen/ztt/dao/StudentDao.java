package com.lunwen.ztt.dao;

import com.lunwen.ztt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-18
 * Time: 下午6:48
 */
public interface StudentDao extends JpaRepository<Student, Long> {
}
