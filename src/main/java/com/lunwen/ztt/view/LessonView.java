package com.lunwen.ztt.view;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-20
 * Time: 上午10:00
 */
public class LessonView {

    private Long lno;

    private String lessonName;

    private Long lessonNumber;

    private String teacherName;

    public LessonView(Long lno, String lessonName, Long lessonNumber, String teacherName) {
        this.lno = lno;
        this.lessonName = lessonName;
        this.lessonNumber = lessonNumber;
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

    public Long getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(Long lessonNumber) {
        this.lessonNumber = lessonNumber;
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
