package com.classAttendance.models.biz.tb_teacher.service.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.biz.tb_college.service.CollegeService;
import com.classAttendance.models.biz.tb_teacher.dao.TeacherDao;
import com.classAttendance.models.biz.tb_teacher.service.TeacherService;
import com.classAttendance.models.pojo.SysUserEntity;
import com.classAttendance.models.pojo.TbCollegeEntity;
import com.classAttendance.models.pojo.TbTeacherEntity;
import com.classAttendance.models.sys.sys_user.service.UserService;
import com.classAttendance.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: StudentServiceImpl
 * @projectName ClassAttendance
 * @description: 学生
 * @date 19-6-2下午5:48
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private UserService userService;
    @Autowired
    private CollegeService collegeService;

    @Override
    public void save(TbTeacherEntity tbTeacherEntity, Long collegeId) {

        long id = IDUtils.genItemId();

        //外键--添加到用户表
        //新增用户--学号做登录账号
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setId(id);
        sysUserEntity.setLoginNumber(tbTeacherEntity.getTeacherCode());
        sysUserEntity.setLoginPassword("123456");
        sysUserEntity.setUserType(2);
        sysUserEntity.setUserName(tbTeacherEntity.getTeacherName());
        sysUserEntity.setUserStatus(1);
        userService.save(sysUserEntity);

        //所属学院
        TbCollegeEntity collegeEntity_result = new TbCollegeEntity();
        collegeEntity_result.setId(collegeId);
        collegeEntity_result = collegeService.findById(collegeEntity_result);
        tbTeacherEntity.setTbCollegeEntity(collegeEntity_result);

        tbTeacherEntity.setId(id);
        teacherDao.save(tbTeacherEntity);
    }

    @Override
    public void delete(TbTeacherEntity tbTeacherEntity) {
        teacherDao.delete(tbTeacherEntity);
    }

    @Override
    public void update(TbTeacherEntity tbTeacherEntity, Long collegeId) {

        // 用户信息
        SysUserEntity userEntity_result = new SysUserEntity();
        userEntity_result.setLoginNumber(tbTeacherEntity.getTeacherCode());
        userEntity_result = userService.findByCode(userEntity_result);
        userEntity_result.setUserName(tbTeacherEntity.getTeacherName());
        userService.update(userEntity_result);

        //所属学院
        TbCollegeEntity collegeEntity_result = new TbCollegeEntity();
        collegeEntity_result.setId(collegeId);
        collegeEntity_result = collegeService.findById(collegeEntity_result);
        tbTeacherEntity.setTbCollegeEntity(collegeEntity_result);

        teacherDao.update(tbTeacherEntity);
    }

    @Override
    public TbTeacherEntity findById(TbTeacherEntity tbTeacherEntity) {
        return teacherDao.findById(tbTeacherEntity);
    }

    @Override
    public TbTeacherEntity findByCode(TbTeacherEntity tbTeacherEntity) {
        return teacherDao.findByCode(tbTeacherEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TbTeacherEntity> pageBean) {
        return teacherDao.findByPage(key,pageBean);
    }

    @Override
    public List<TbTeacherEntity> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        teacherDao.deleteBatch(Ids);
    }
}
