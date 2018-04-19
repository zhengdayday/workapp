package com.lunwen.ztt.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 姓名 */
    @Column(name = "name")
    private String name;

    /** 密码 */
    @Column(name = "password")
    private String password;

    /** email */
    @Column(name = "email")
    private String email;

    /** level 用于判断学生0 教师1 管理员2*/
    @Column(name = "level")
    private int level;

    /** 学号 工号 */
    @Column(name = "number", unique = true)
    private String number;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
