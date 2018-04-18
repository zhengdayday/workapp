package com.lunwen.ztt.model;

import javax.persistence.*;

/**
 * 学生作业表
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-18
 * Time: 下午4:52
 */
@Entity
@Table(name = "student_work")
public class StudentWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 学号 */
    @Column(name = "sno")
    private String sno;

    /** 作业号 */
    @Column(name = "wno")
    private Long wno;

    /** 成绩 */
    @Column(name = "grade")
    private Long grade;

    /** 回答 */
    @Column(name = "answer")
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public Long getWno() {
        return wno;
    }

    public void setWno(Long wno) {
        this.wno = wno;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }
}
