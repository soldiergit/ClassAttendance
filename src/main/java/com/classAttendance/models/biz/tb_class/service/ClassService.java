package com.classAttendance.models.biz.tb_class.service;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.TbClassEntity;

import java.util.List;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18上午10:57
 * @Describe:
 **/
public interface ClassService {

    /**
     * 添加
     * @param tbClassEntity
     * @param collegeId
     */
    public void save(TbClassEntity tbClassEntity, Long collegeId);

    /**
     * 删除
     * @param tbClassEntity
     */
    public void delete(TbClassEntity tbClassEntity);

    /**
     * 修改
     * @param tbClassEntity
     */
    public void update(TbClassEntity tbClassEntity, Long collegeId);

    /**
     * 查询
     * @param tbClassEntity
     */
    public TbClassEntity findById(TbClassEntity tbClassEntity);

    /**
     * 分页查询
     * @param key
     * @param pageBean
     */
    public PageBean findByPage(String key, PageBean<TbClassEntity> pageBean);

    /**
     * 列表
     */
    public List<TbClassEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
