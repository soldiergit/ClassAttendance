package com.classAttendance.models.biz.tb_course_choose.service.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.biz.tb_course_choose.dao.CourseChooseDao;
import com.classAttendance.models.biz.tb_course_choose.service.CourseChooseService;
import com.classAttendance.models.pojo.TbCourseChooseEntity;
import com.classAttendance.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: courseChooseDaoImpl
 @ProjectName:ClassAttendance
 * @description: 学院
 * @date 19-6-4上午9:04
 */
@Service
public class CourseChooseServiceImpl implements CourseChooseService {

    @Autowired
    private CourseChooseDao courseChooseDao;

    @Override
    public void save(TbCourseChooseEntity tbCourseChooseEntity) {
        tbCourseChooseEntity.setId(IDUtils.genItemId());
        courseChooseDao.save(tbCourseChooseEntity);
    }

    @Override
    public void delete(TbCourseChooseEntity tbCourseChooseEntity) {
        courseChooseDao.delete(tbCourseChooseEntity);
    }

    @Override
    public void update(TbCourseChooseEntity tbCourseChooseEntity) {
        courseChooseDao.update(tbCourseChooseEntity);
    }

    @Override
    public TbCourseChooseEntity findById(TbCourseChooseEntity tbCourseChooseEntity) {
        return courseChooseDao.findById(tbCourseChooseEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TbCourseChooseEntity> pageBean) {
        return courseChooseDao.findByPage(key,pageBean);
    }

    @Override
    public List<TbCourseChooseEntity> findAll() {
        return courseChooseDao.findAll();
    }

    @Override
    public void deleteBatch(String[] collegeIds) {
        courseChooseDao.deleteBatch(collegeIds);
    }
}
