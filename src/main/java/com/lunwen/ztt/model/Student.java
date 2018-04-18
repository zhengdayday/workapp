package com.lunwen.ztt.model;

import javax.persistence.*;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-18
 * Time: 下午4:09
 */
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 学号 */
    @Column(name = "sno", unique = true)
    private String sno;

    /** 姓名 */
    @Column(name = "name")
    private String name;

    /** 密码 */
    @Column(name = "password")
    private String password;

    /** 所属班级*/
    @Column(name = "sclass")
    private String className;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
