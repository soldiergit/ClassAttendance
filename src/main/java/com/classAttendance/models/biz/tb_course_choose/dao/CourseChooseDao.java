package com.classAttendance.models.biz.tb_course_choose.dao;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.TbCourseChooseEntity;

import java.util.List;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18上午10:57
 * @Describe:
 */
public interface CourseChooseDao {

    /**
     * 添加
     * @param tbCourseChooseEntity
     */
    public void save(TbCourseChooseEntity tbCourseChooseEntity);

    /**
     * 删除
     * @param tbCourseChooseEntity
     */
    public void delete(TbCourseChooseEntity tbCourseChooseEntity);

    /**
     * 修改
     * @param tbCourseChooseEntity
     */
    public void update(TbCourseChooseEntity tbCourseChooseEntity);

    /**
     * 查询
     * @param tbClassEntity
     */
    public TbCourseChooseEntity findById(TbCourseChooseEntity classEntity);

    /**
     * 分页查询
     * @param key
     * @param pageBean
     */
    public PageBean findByPage(String key, PageBean<TbCourseChooseEntity> pageBean);

    /**
     * 列表
     */
    public List<TbCourseChooseEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
