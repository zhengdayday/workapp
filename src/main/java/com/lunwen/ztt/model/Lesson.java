package com.lunwen.ztt.model;

import javax.persistence.*;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-18
 * Time: 下午4:50
 */
@Entity
@Table(name = "lesson")
public class Lesson {

    /** 课程号 */
    @Id
    @Column(name = "lno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lno;


    /** 课程名 */
    @Column(name = "lname")
    private String lessonName;

    /** 工号 */
    @Column(name = "tno")
    private String tno;

    public Long getLno() {
        return lno;
    }

    public void setLno(Long lno) {
        this.lno = lno;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }
}
