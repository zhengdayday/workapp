package com.lunwen.ztt.view;

import com.lunwen.ztt.model.StudentSelectLesson;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-22
 * Time: 上午10:08
 */
public class StudentWorkView {

    private String workName;

    private String workDesc;

    private String workStatus;

    private String gradle;

    private String lessonName;


    public StudentWorkView(String workName, String workDesc, String workStatus, String lessonName) {
        this.workName = workName;
        this.workDesc = workDesc;
        this.workStatus = workStatus;
        this.lessonName = lessonName;
    }

    public StudentWorkView(String workName, String workDesc, String workStatus, String gradle, String lessonName) {
        this.workName = workName;
        this.workDesc = workDesc;
        this.workStatus = workStatus;
        this.gradle = gradle;
        this.lessonName = lessonName;
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

    public String getGradle() {
        return gradle;
    }

    public void setGradle(String gradle) {
        this.gradle = gradle;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
}
