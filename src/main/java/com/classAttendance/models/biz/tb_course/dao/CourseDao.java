package com.classAttendance.models.biz.tb_course.dao;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.TbCourseEntity;

import java.util.List;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18上午10:57
 * @Describe:
 */
public interface CourseDao {

    /**
     * 添加
     * @param tbCourseEntity
     */
    public void save(TbCourseEntity tbCourseEntity);

    /**
     * 删除
     * @param tbCourseEntity
     */
    public void delete(TbCourseEntity tbCourseEntity);

    /**
     * 修改
     * @param tbCourseEntity
     */
    public void update(TbCourseEntity tbCourseEntity);

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
     * 分页查询-用于学生选课-=-无效
     * @param key
     * @param studentCode   学号
     * @param haveChosen    判断是已选还是未选
     * @param pageBean
     */
    public PageBean findByChoose(String key, String studentCode, boolean haveChosen, PageBean<TbCourseEntity> pageBean);

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
