package com.lunwen.wangjie.view;

/**
 * Created with IDEA
 * author: wangjie
 */
public class StudentLesson {

    private String teacherName;

    private String lessonName;

    private String lessonDesc;

    private Long lno;

    public StudentLesson() {

    }

    public StudentLesson(String teacherName, String lessonName, String lessonDesc, Long lno) {
        this.teacherName = teacherName;
        this.lessonName = lessonName;
        this.lessonDesc = lessonDesc;
        this.lno = lno;
    }

    public Long getLno() {
        return lno;
    }

    public void setLno(Long lno) {
        this.lno = lno;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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
}
