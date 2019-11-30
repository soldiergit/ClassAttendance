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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public List<TbStudentEntity> findAll(String key, String courseId, Integer attendanceType) {

        List<TbStudentEntity> allStu = studentDao.findAll(key, courseId);
        //  随机考勤
        if (attendanceType != null && attendanceType.equals(2)) {
            //产生1-100随机数
            int number = new Random().nextInt(100) + 1;
            allStu = extract(allStu, number);
        }

        return allStu;
    }
    /**
     * 随机抽取
     * @param allStu    课程对应的所有学生
     * @param number    抽取学生数量
     */
    private List<TbStudentEntity> extract(List<TbStudentEntity> allStu, Integer number) {
        //返回结果
        List<TbStudentEntity> studentList = new ArrayList<>();
        //抽取
        for (int i = 0; i < (number > allStu.size() ? allStu.size() : number); i++) {
            //获取0-allStu.size的随机数
            int random = (int) (Math.random() * allStu.size());
            //添加学生,随机获取下标为random的学生并且把它添加到要传回页面的list里面
            studentList.add(allStu.get(random));
            //将本次已经抽取过的学生从预抽取的list里面移除
            allStu.remove(random);
        }
        return studentList;
    }

    @Override
    public void deleteBatch(String[] Ids) {
        studentDao.deleteBatch(Ids);
    }
}
