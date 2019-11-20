package com.classAttendance.models.biz.tb_attendance.service.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.biz.tb_attendance.dao.AttendanceDao;
import com.classAttendance.models.biz.tb_attendance.service.AttendanceService;
import com.classAttendance.models.pojo.TbAttendanceEntity;
import com.classAttendance.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午3:43
 * @Describe:
 **/
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    @Override
    public void save(TbAttendanceEntity tbAttendanceEntity) {
        tbAttendanceEntity.setId(IDUtils.genItemId());
        attendanceDao.save(tbAttendanceEntity);
    }

    @Override
    public void delete(TbAttendanceEntity tbAttendanceEntity) {
        attendanceDao.delete(tbAttendanceEntity);
    }

    @Override
    public void update(TbAttendanceEntity tbAttendanceEntity) {
        attendanceDao.update(tbAttendanceEntity);
    }

    @Override
    public TbAttendanceEntity findById(TbAttendanceEntity tbAttendanceEntity) {
        return attendanceDao.findById(tbAttendanceEntity);
    }

    @Override
    public PageBean findByPage(String key, String teacherCode, PageBean<TbAttendanceEntity> pageBean) {
        return attendanceDao.findByPage(key, teacherCode, pageBean);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        attendanceDao.deleteBatch(Ids);
    }
}
