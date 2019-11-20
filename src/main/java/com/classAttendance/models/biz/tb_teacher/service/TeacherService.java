package com.classAttendance.models.biz.tb_teacher.service;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.TbTeacherEntity;

import java.util.List;

/**
 * @author soldier
 * @title: StudentService
 * @projectName ClassAttendance
 * @description: 学生
 * @date 19-6-2下午5:47
 */
public interface TeacherService {

    /**
     * 添加
     */
    public void save(TbTeacherEntity tbTeacherEntity, Long collegeId);

    /**
     * 删除
     */
    public void delete(TbTeacherEntity tbTeacherEntity);

    /**
     * 修改
     */
    public void update(TbTeacherEntity tbTeacherEntity, Long collegeId);

    /**
     * 查询
     */
    public TbTeacherEntity findById(TbTeacherEntity tbTeacherEntity);

    /**
     * 查询
     */
    public TbTeacherEntity findByCode(TbTeacherEntity tbTeacherEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     */
    public PageBean findByPage(String key, PageBean<TbTeacherEntity> pageBean);

    /**
     * 列表
     */
    public List<TbTeacherEntity> findAll();

    /**
     * 批量删除
     */
    public void deleteBatch(String[] Ids);
}
