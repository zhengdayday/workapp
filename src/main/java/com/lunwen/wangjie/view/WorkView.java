package com.lunwen.wangjie.view;

/**
 * Created with IDEA
 * author: wangjie
 */
public class WorkView {

    private String workName;

    private String workDesc;

    private String studentName;

    private String grade;

    private String lessonName;

    private String sno;

    private Long wno;

    private String workStatus;

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public Long getWno() {
        return wno;
    }

    public void setWno(Long wno) {
        this.wno = wno;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public WorkView() {

    }

    public WorkView(String workName, String workDesc, String studentName, String lessonName, String sno, Long wno, String workStatus) {
        this.workName = workName;
        this.workDesc = workDesc;
        this.studentName = studentName;
        this.lessonName = lessonName;
        this.sno = sno;
        this.wno = wno;
        this.workStatus = workStatus;
    }

    public WorkView(String workName, String workDesc, String studentName, String grade, String lessonName, String sno, Long wno) {
        this.workName = workName;
        this.workDesc = workDesc;
        this.studentName = studentName;
        this.grade = grade;
        this.lessonName = lessonName;
        this.sno = sno;
        this.wno = wno;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
