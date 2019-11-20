package com.classAttendance.models.biz.tb_teacher.dao;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.TbTeacherEntity;

import java.util.List;

/**
 * @author soldier
 * @title: StudentDao
 * @projectName ClassAttendance
 * @description: 学生
 * @date 19-6-2下午12:03
 */
public interface TeacherDao {

    /**
     * 添加
     */
    public void save(TbTeacherEntity tbTeacherEntity);

    /**
     * 删除
     */
    public void delete(TbTeacherEntity tbTeacherEntity);

    /**
     * 修改
     */
    public void update(TbTeacherEntity tbTeacherEntity);

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