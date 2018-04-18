package com.lunwen.ztt.model;

import javax.persistence.*;

/**
 * 教师表
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-18
 * Time: 下午4:56
 */

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 工号 */
    @Column(name = "tno", unique = true)
    private String tno;

    /** 姓名 */
    @Column(name = "name")
    private String teacherName;

    /** 密码 */
    @Column(name = "password")
    private String password;

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
