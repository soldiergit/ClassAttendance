package com.classAttendance.models.sys.sys_user.service;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.SysUserEntity;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午3:23
 * @Describe:
 */
public interface UserService {

    /**
     * 添加
     */
    public void save(SysUserEntity sysUserEntity);

    /**
     * 删除
     */
    public void delete(SysUserEntity sysUserEntity);

    /**
     * 修改
     */
    public void update(SysUserEntity sysUserEntity);

    /**
     * 查询
     */
    public SysUserEntity findById(SysUserEntity sysUserEntity);

    /**
     * 查询--工号或者学号
     */
    public SysUserEntity findByCode(SysUserEntity sysUserEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     */
    public PageBean findByPage(String key, PageBean<SysUserEntity> pageBean);
    /**
     * 批量删除
     */
    public void deleteBatch(String[] userIds);
}
