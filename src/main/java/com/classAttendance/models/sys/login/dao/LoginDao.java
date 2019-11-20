package com.classAttendance.models.sys.login.dao;

import com.classAttendance.common.vo.R;
import com.classAttendance.models.pojo.SysUserEntity;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午3:26
 * @Describe:
 */
public interface LoginDao {

    /**
     * 用户登陆
     * @param sysUserEntity

     */
    R userLogin(SysUserEntity sysUserEntity);

    /**
     * 修改密码
     * @param sysUserEntity
     * @param newPassword

     */
    R updatePassword(SysUserEntity sysUserEntity, String newPassword);
}
