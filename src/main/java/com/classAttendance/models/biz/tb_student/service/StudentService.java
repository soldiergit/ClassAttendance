package com.classAttendance.models.biz.tb_student.service;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.TbStudentEntity;

import java.util.List;

/**
 * @author soldier
 * @title: StudentService
 * @projectName ClassAttendance
 * @description: 学生
 * @date 19-6-2下午5:47
 */
public interface StudentService {
    /**
     * 添加
     * @param tbStudentEntity
     */
    public void save(TbStudentEntity tbStudentEntity, Long classId);

    /**
     * 删除
     * @param tbStudentEntity
     */
    public void delete(TbStudentEntity tbStudentEntity);

    /**
     * 修改
     * @param tbStudentEntity
     */
    public void update(TbStudentEntity tbStudentEntity, Long classId);

    /**
     * 查询
     * @param tbStudentEntity
     */
    public TbStudentEntity findById(TbStudentEntity tbStudentEntity);

    /**
     * 查询
     * @param tbStudentEntity
     */
    public TbStudentEntity findByCode(TbStudentEntity tbStudentEntity);

    /**
     * 分页查询
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
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
