package com.lunwen.ztt.model;

import javax.persistence.*;

/**
 * 学生作业表
 * Created with IDEA
 * author: wangjie
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

    @Column(name = "work_status")
    private String work_status;

    /** 老师是否批改作业 */
    @Column(name = "work_read")
    private String workRead;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getWork_status() {
        return work_status;
    }

    public void setWork_status(String work_status) {
        this.work_status = work_status;
    }

    public String getWorkRead() {
        return workRead;
    }

    public void setWorkRead(String workRead) {
        this.workRead = workRead;
    }

/**
     * 老师新建课程 -> 在该课程下新建作业 -> 作业智能推送订阅了该课程的学生
     */
}
