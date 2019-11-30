package com.classAttendance.models.biz.tb_course.service.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.biz.tb_course.dao.CourseDao;
import com.classAttendance.models.biz.tb_course.service.CourseService;
import com.classAttendance.models.biz.tb_teacher.service.TeacherService;
import com.classAttendance.models.pojo.TbCourseEntity;
import com.classAttendance.models.pojo.TbTeacherEntity;
import com.classAttendance.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: courseDaoImpl
 @ProjectName:ClassAttendance
 * @description: 学院
 * @date 19-6-4上午9:04
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TeacherService teacherService;

    @Override
    public void save(TbCourseEntity tbCourseEntity, Long teacherId) {

        //开课老师
        TbTeacherEntity teacherEntity_result = new TbTeacherEntity();
        teacherEntity_result.setId(teacherId);
        teacherEntity_result = teacherService.findById(teacherEntity_result);
        tbCourseEntity.setTbTeacherEntity(teacherEntity_result);

        Long id = IDUtils.genItemId();
        tbCourseEntity.setId(id);
        tbCourseEntity.setQueryCode(String.valueOf(id));
        courseDao.save(tbCourseEntity);
    }

    @Override
    public void delete(TbCourseEntity tbCourseEntity) {
        courseDao.delete(tbCourseEntity);
    }

    @Override
    public void update(TbCourseEntity tbCourseEntity, Long teacherId) {

        //开课老师
        TbTeacherEntity teacherEntity_result = new TbTeacherEntity();
        teacherEntity_result.setId(teacherId);
        teacherEntity_result = teacherService.findById(teacherEntity_result);
        tbCourseEntity.setTbTeacherEntity(teacherEntity_result);

        tbCourseEntity.setQueryCode(String.valueOf(tbCourseEntity.getId()));
        courseDao.update(tbCourseEntity);
    }

    @Override
    public TbCourseEntity findById(TbCourseEntity tbCourseEntity) {
        return courseDao.findById(tbCourseEntity);
    }

    @Override
    public PageBean findByPage(String key, String teacherCode, PageBean<TbCourseEntity> pageBean) {
        return courseDao.findByPage(key,teacherCode,pageBean);
    }

    @Override
    public PageBean findByHaveChosen(String key, String studentCode, PageBean<TbCourseEntity> pageBean) {
        return courseDao.findByChoose(key, studentCode, true, pageBean);
    }

    @Override
    public PageBean findByNoChoice(String key, String studentCode, PageBean<TbCourseEntity> pageBean) {
        return courseDao.findByChoose(key, studentCode, false, pageBean);
    }

    @Override
    public List<TbCourseEntity> findAll(String teacherCode) {
        return courseDao.findAll(teacherCode);
    }

    @Override
    public void deleteBatch(String[] collegeIds) {
        courseDao.deleteBatch(collegeIds);
    }
}
