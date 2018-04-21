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
    private String grade;

    /** 回答 */
    @Column(name = "answer")
    private String answer;

    /** 是否完成 0没完成 1完成*/
    @Column(name = "work_status")
    private int work_status;

    /** 老师是否批改作业  0没改 1改了*/
    @Column(name = "work_read")
    private int workRead;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getWork_status() {
        return work_status;
    }

    public void setWork_status(int work_status) {
        this.work_status = work_status;
    }

    public int getWorkRead() {
        return workRead;
    }

    public void setWorkRead(int workRead) {
        this.workRead = workRead;
    }

    public StudentWork() {

    }

    public StudentWork(String sno, Long wno, int work_status, int workRead) {
        this.sno = sno;
        this.wno = wno;
        this.work_status = work_status;
        this.workRead = workRead;
    }

/**
     * 老师新建课程 -> 在该课程下新建作业 -> 作业智能推送订阅了该课程的学生
     */
}
