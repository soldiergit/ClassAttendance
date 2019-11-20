package com.classAttendance.models.sys.sys_user.service.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.SysUserEntity;
import com.classAttendance.models.sys.sys_user.dao.UserDao;
import com.classAttendance.models.sys.sys_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午3:23
 * @Describe:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 添加
     * @param sysUserEntity
     */
    @Override
    public void save(SysUserEntity sysUserEntity) {
        userDao.save(sysUserEntity);
    }

    /**
     * 删除
     * @param sysUserEntity
     */
    @Override
    public void delete(SysUserEntity sysUserEntity) {
        userDao.delete(sysUserEntity);
    }

    /**
     * 修改
     * @param sysUserEntity
     */
    @Override
    public void update(SysUserEntity sysUserEntity) {
        userDao.update(sysUserEntity);
    }

    /**
     * 条件查询
     * @param sysUserEntity
     */
    @Override
    public SysUserEntity findById(SysUserEntity sysUserEntity) {
        return userDao.findById(sysUserEntity);
    }

    @Override
    public SysUserEntity findByCode(SysUserEntity sysUserEntity) {
        return userDao.findByCode(sysUserEntity);
    }

    /**
     * 分页
     * @param key
     * @param pageBean
     */
    @Override
    public PageBean findByPage(String key, PageBean<SysUserEntity> pageBean) {
        return userDao.findByPage(key,pageBean);
    }

    /**
     * 批量删除
     * @param userIds
     */
    @Override
    public void deleteBatch(String[] userIds) {
        userDao.deleteBatch(userIds);
    }
}
