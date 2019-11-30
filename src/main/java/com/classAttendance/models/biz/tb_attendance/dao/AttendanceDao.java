package com.classAttendance.models.biz.tb_attendance.dao;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.TbAttendanceEntity;

import java.util.List;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18上午10:57
 * @Describe:
 **/
public interface AttendanceDao {

    /**
     * 添加
     * @param tbAttendanceEntity
     */
    public void save(TbAttendanceEntity tbAttendanceEntity);

    /**
     * 删除
     * @param tbAttendanceEntity
     */
    public void delete(TbAttendanceEntity tbAttendanceEntity);

    /**
     * 修改
     * @param tbAttendanceEntity
     */
    public void update(TbAttendanceEntity tbAttendanceEntity);

    /**
     * 查询
     * @param tbAttendanceEntity
     */
    public TbAttendanceEntity findById(TbAttendanceEntity tbAttendanceEntity);

    /**
     * 分页查询
     * @param key
     * @param pageBean
     * @param teacherCode   教师工号
     * @param studentCode   学生学号
     */
    public PageBean findByPage(String key, String teacherCode, String studentCode, PageBean<TbAttendanceEntity> pageBean);

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

    /**
     * 批量导出
     * @param teacherCode   用于教师导出全部
     * @param studentCode   用于学生导出全部
     * @param Ids           用于批量导出
     */
    public List<TbAttendanceEntity> findByExport(String teacherCode, String studentCode, String[] Ids);
}
