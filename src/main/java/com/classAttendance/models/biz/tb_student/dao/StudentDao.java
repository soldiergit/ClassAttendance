package com.classAttendance.models.biz.tb_student.dao;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.TbStudentEntity;

import java.util.List;

/**
 * @author soldier
 * @title: StudentDao
 * @projectName ClassAttendance
 * @description: 学生
 * @date 19-6-2下午12:03
 */
public interface StudentDao {

    /**
     * 添加
     */
    public void save(TbStudentEntity tbStudentEntity);

    /**
     * 删除
     */
    public void delete(TbStudentEntity tbStudentEntity);

    /**
     * 修改
     */
    public void update(TbStudentEntity tbStudentEntity);

    /**
     * 查询
     */
    public TbStudentEntity findById(TbStudentEntity tbStudentEntity);

    /**
     * 查询
     */
    public TbStudentEntity findByCode(TbStudentEntity tbStudentEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     */
    public PageBean findByPage(String key, PageBean<TbStudentEntity> pageBean);

    /**
     * 列表
     */
    public List<TbStudentEntity> findAll(String courseId);

    /**
     * 批量删除
     */
    public void deleteBatch(String[] Ids);

}