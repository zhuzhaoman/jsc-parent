package com.zzm.dao;

import com.zzm.pojo.Errors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@SuppressWarnings("all")
public interface ErrorsRepository extends JpaRepository<Errors, String>, JpaSpecificationExecutor<Errors> {

    /**
     * 根据错误分类和错误是否查看进行查询
     *
     * @param category 分类
     * @param flag     是否查看
     * @return
     */
    public List<Errors> getErrorsByCategoryAndFlag(Integer category, Integer flag);

    /**
     * 根据错误分类和错误是否查看进行条数查绚
     *
     * @param category 是否查看
     * @param flag     分类
     * @return
     */
    public Integer countErrorsByCategoryAndFlag(Integer category, Integer flag);

    /**
     * 根据错误分类获取异常信息条数
     *
     * @param category 分类
     * @return
     */
    public Integer countErrorsByCategory(Integer category);

    /**
     * 将未读信息改编为已读信息
     *
     * @param category 分类
     */
    @Modifying
    @Query("update Errors c set c.flag = 1 where c.category = ?1 and c.flag = 0")
    @Transactional
    public void updateErrorsFlag(Integer category);

    /**
     * 根据分类删除异常信息
     *
     * @param category 分类
     */
    @Transactional
    public void deleteAllByCategory(Integer category);

}
