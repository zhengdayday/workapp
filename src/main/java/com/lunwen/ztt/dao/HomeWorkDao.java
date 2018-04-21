package com.lunwen.ztt.dao;

import com.lunwen.ztt.model.HomeWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IDEA
 * author: wangjie
 * Date: 18-4-18
 * Time: 下午6:47
 */
public interface HomeWorkDao extends JpaRepository<HomeWork, Long> {

    /**
     * 根据工号 获取所有作业
     * @param tno 工号
     * @return list work
     */
    List<HomeWork> findHomeWorkByTno(String tno);

    /**
     * 工号 作业名 获取作业
     * @param tno 工号
     * @param workName 作业名
     * @return work
     */
    HomeWork findHomeWorkByTnoAndWorkName(String tno, String workName);

    /**
     * 查找作业
     * @param wno 作业号
     * @return work
     */
    HomeWork findHomeWorkByWno(Long wno);
}
