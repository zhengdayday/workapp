package com.lunwen.ztt.model;

import javax.persistence.*;

/** 课程表
 * Created with IDEA
 * author: wangjie
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

    /** 课程详情*/
    @Column(name = "lesson_desc")
    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}