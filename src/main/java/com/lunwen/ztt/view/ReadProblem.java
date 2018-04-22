package com.lunwen.ztt.view;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-22
 * Time: 下午1:55
 */
public class ReadProblem {

    private String desc;

    private String answer;

    private String studentName;

    private String workName;

    private String workInfo;

    public ReadProblem(String workName, String workInfo, String desc, String answer, String studentName) {
        this.workName = workName;
        this.workInfo = workInfo;
        this.desc = desc;
        this.answer = answer;
        this.studentName = studentName;
    }

    public ReadProblem() {

    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(String workInfo) {
        this.workInfo = workInfo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
