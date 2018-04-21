package com.lunwen.ztt.view;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-21
 * Time: 下午12:27
 */
public class WorkView {

    private String workName;

    private String workDesc;

    private String studentName;

    private String gradle;

    public WorkView() {

    }

    public WorkView(String workName, String workDesc, String studentName) {
        this.workName = workName;
        this.workDesc = workDesc;
        this.studentName = studentName;
    }

    public WorkView(String workName, String workDesc, String studentName, String gradle) {
        this.workName = workName;
        this.workDesc = workDesc;
        this.studentName = studentName;
        this.gradle = gradle;
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

    public String getGradle() {
        return gradle;
    }

    public void setGradle(String gradle) {
        this.gradle = gradle;
    }
}
