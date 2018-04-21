package com.lunwen.ztt.dao;

import com.lunwen.ztt.model.StudentWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IDEA
 * author: wangjie
 * Date: 18-4-18
 * Time: 下午6:49
 */
public interface StudentWorkDao extends JpaRepository<StudentWork, Long>{

    List<StudentWork> findAllByWno(Long wno);
}
