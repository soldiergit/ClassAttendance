package com.classAttendance.models.biz.tb_student.service.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.biz.tb_class.service.ClassService;
import com.classAttendance.models.biz.tb_student.dao.StudentDao;
import com.classAttendance.models.biz.tb_student.service.StudentService;
import com.classAttendance.models.pojo.SysUserEntity;
import com.classAttendance.models.pojo.TbClassEntity;
import com.classAttendance.models.pojo.TbStudentEntity;
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
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private UserService userService;
    @Autowired
    private ClassService classService;

    @Override
    public void save(TbStudentEntity tbStudentEntity, Long classId) {

        long id = IDUtils.genItemId();

        //新增用户--学号做登录账号
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setId(id);
        sysUserEntity.setLoginNumber(tbStudentEntity.getStudentCode());
        sysUserEntity.setLoginPassword("123456");
        sysUserEntity.setUserType(3);
        sysUserEntity.setUserName(tbStudentEntity.getStudentName());
        sysUserEntity.setUserStatus(1);
        userService.save(sysUserEntity);

        //所属班级
        TbClassEntity classEntity_result = new TbClassEntity();
        classEntity_result.setId(classId);
        classEntity_result = classService.findById(classEntity_result);
        tbStudentEntity.setTbClassEntity(classEntity_result);

        tbStudentEntity.setId(id);
        studentDao.save(tbStudentEntity);
    }

    @Override
    public void delete(TbStudentEntity tbStudentEntity) {
        studentDao.delete(tbStudentEntity);
    }

    @Override
    public void update(TbStudentEntity tbStudentEntity, Long classId) {

        // 用户信息
        SysUserEntity userEntity_result = new SysUserEntity();
        userEntity_result.setLoginNumber(tbStudentEntity.getStudentCode());
        userEntity_result = userService.findByCode(userEntity_result);
        userEntity_result.setUserName(tbStudentEntity.getStudentName());
        userService.update(userEntity_result);

        //所属班级
        TbClassEntity classEntity_result = new TbClassEntity();
        classEntity_result.setId(classId);
        classEntity_result = classService.findById(classEntity_result);
        tbStudentEntity.setTbClassEntity(classEntity_result);

        studentDao.update(tbStudentEntity);
    }

    @Override
    public TbStudentEntity findById(TbStudentEntity tbStudentEntity) {
        return studentDao.findById(tbStudentEntity);
    }

    @Override
    public TbStudentEntity findByCode(TbStudentEntity tbStudentEntity) {
        return studentDao.findByCode(tbStudentEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TbStudentEntity> pageBean) {
        return studentDao.findByPage(key,pageBean);
    }

    @Override
    public List<TbStudentEntity> findAll(String courseId) {
        return studentDao.findAll(courseId);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        studentDao.deleteBatch(Ids);
    }
}
