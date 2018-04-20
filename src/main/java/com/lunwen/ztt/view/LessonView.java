package com.lunwen.ztt.view;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-20
 * Time: 上午10:00
 */
public class LessonView {

    private String lessonName;

    private Long lessonNumber;

    private String teacherName;


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

    public LessonView(String lessonName, Long lessonNumber, String teacherName) {
        this.lessonName = lessonName;
        this.lessonNumber = lessonNumber;
        this.teacherName = teacherName;
    }

    public LessonView() {

    }
}
