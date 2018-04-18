package com.lunwen.ztt.model;

import javax.persistence.*;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-18
 * Time: 下午4:46
 */
@Entity
@Table(name = "student_select_lesson")
public class StudentSelectLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 学号 */
    @Column(name = "sno")
    private String sno;

    /** 课程号 */
    @Column(name = "lno")
    private Long lno;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public Long getLno() {
        return lno;
    }

    public void setLno(Long lno) {
        this.lno = lno;
    }
}
