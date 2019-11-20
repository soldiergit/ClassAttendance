package com.classAttendance.models.biz.tb_college.service;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.TbCollegeEntity;

import java.util.List;

/**
 * @author soldier
 * @title: CollegeDao
 @ProjectName:ClassAttendance
 * @description: 学院
 * @date 19-6-3上午10:59
 */
public interface CollegeService {

    /**
     * 添加
     * @param tbCollegeEntity
     */
    public void save(TbCollegeEntity tbCollegeEntity);

    /**
     * 删除
     * @param tbCollegeEntity
     */
    public void delete(TbCollegeEntity tbCollegeEntity);

    /**
     * 修改
     * @param tbCollegeEntity
     */
    public void update(TbCollegeEntity tbCollegeEntity);

    /**
     * 查询
     * @param tbCollegeEntity
     */
    public TbCollegeEntity findById(TbCollegeEntity tbCollegeEntity);

    /**
     * 分页查询
     * @param key
     * @param pageBean
     */
    public PageBean findByPage(String key, PageBean<TbCollegeEntity> pageBean);

    /**
     * 列表
     */
    public List<TbCollegeEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
