package com.classAttendance.models.biz.tb_course.service;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.TbCourseEntity;

import java.util.List;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午4:52
 * @Describe:
 */
public interface CourseService {

    /**
     * 添加
     * @param tbCourseEntity
     */
    public void save(TbCourseEntity tbCourseEntity, Long teacherId);

    /**
     * 删除
     * @param tbCourseEntity
     */
    public void delete(TbCourseEntity tbCourseEntity);

    /**
     * 修改
     * @param tbCourseEntity
     */
    public void update(TbCourseEntity tbCourseEntity, Long teacherId);

    /**
     * 查询
     * @param tbCourseEntity
     */
    public TbCourseEntity findById(TbCourseEntity tbCourseEntity);

    /**
     * 分页查询
     * @param key
     * @param pageBean
     */
    public PageBean findByPage(String key, String teacherCode, PageBean<TbCourseEntity> pageBean);

    /**
     * 列表
     */
    public List<TbCourseEntity> findAll(String teacherCode);

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
