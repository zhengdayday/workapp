package com.lunwen.ztt.view;

import com.lunwen.ztt.model.StudentSelectLesson;

/**
 * Created with IDEA
 * author: wangjie
 */
public class StudentWorkView {

    private String workName;

    private String workDesc;

    private String workStatus;

    private String grade;

    private String lessonName;

    private Long wno;

    public Long getWno() {
        return wno;
    }

    public void setWno(Long wno) {
        this.wno = wno;
    }

    public StudentWorkView(String workName, String workDesc, String workStatus, String lessonName, Long wno) {
        this.workName = workName;
        this.workDesc = workDesc;
        this.workStatus = workStatus;
        this.lessonName = lessonName;
        this.wno = wno;
    }

    public StudentWorkView(String workName, String workDesc, String workStatus, String grade, String lessonName, Long wno) {
        this.workName = workName;
        this.workDesc = workDesc;
        this.workStatus = workStatus;
        this.grade = grade;
        this.lessonName = lessonName;
        this.wno = wno;
    }

    public StudentWorkView() {

    }
    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
}
