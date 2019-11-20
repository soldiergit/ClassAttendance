package com.classAttendance.models.sys.login.service.impl;

import com.classAttendance.common.vo.R;
import com.classAttendance.models.pojo.SysUserEntity;
import com.classAttendance.models.sys.login.dao.LoginDao;
import com.classAttendance.models.sys.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午3:26
 * @Describe:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public R userLogin(SysUserEntity sysUserEntities) {
        return loginDao.userLogin(sysUserEntities);
    }

    @Override
    public R updatePassword(SysUserEntity sysUserEntities, String newPassword) {
        return loginDao.updatePassword(sysUserEntities,newPassword);
    }
}
