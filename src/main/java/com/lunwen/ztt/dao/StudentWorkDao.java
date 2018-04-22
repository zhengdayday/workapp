package com.lunwen.ztt.dao;

import com.lunwen.ztt.model.StudentWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IDEA
 * author: wangjie
 */
public interface StudentWorkDao extends JpaRepository<StudentWork, Long>{

    List<StudentWork> findAllByWno(Long wno);

    List<StudentWork> findAllBySno(String sno);


    StudentWork findStudentWorkBySnoAndWno(String sno, Long wno);

}
