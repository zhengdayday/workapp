package com.lunwen.ztt.model;

import javax.persistence.*;

/**
 * 作业表
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-18
 * Time: 下午6:09
 */
@Entity
@Table(name = "tbl_work")
public class HomeWork {

    /** 作业号 */
    @Id
    @Column(name = "wno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wno;


    /** 课程名 */
    @Column(name = "work_desc")
    private String desc;

    /** 工号 */
    @Column(name = "tno")
    private String tno;

    public Long getWno() {
        return wno;
    }

    public void setWno(Long wno) {
        this.wno = wno;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }
}
