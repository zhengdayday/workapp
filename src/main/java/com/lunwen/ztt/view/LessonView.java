package com.lunwen.ztt.view;

/**
 * Created with IDEA
 * author: wangjie
 */
public class LessonView {

    private Long lno;

    private String lessonName;

    private String lessonDesc;

    private String teacherName;

    public LessonView(Long lno, String lessonName, String lessonDesc, String teacherName) {
        this.lno = lno;
        this.lessonName = lessonName;
        this.lessonDesc = lessonDesc;
        this.teacherName = teacherName;
    }

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

    public String getLessonDesc() {
        return lessonDesc;
    }

    public void setLessonDesc(String lessonDesc) {
        this.lessonDesc = lessonDesc;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public LessonView() {

    }
}
