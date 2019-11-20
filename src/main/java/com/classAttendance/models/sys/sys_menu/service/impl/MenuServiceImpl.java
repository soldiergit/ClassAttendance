package com.classAttendance.models.sys.sys_menu.service.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.SysMenuEntity;
import com.classAttendance.models.sys.sys_menu.dao.MenuDao;
import com.classAttendance.models.sys.sys_menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: MenuServiceImpl
 * @projectName class_attendance
 * @date 19-7-5上午10:57
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<SysMenuEntity> findMenuByUser(Integer userType) {
        return menuDao.findMenuByUser(userType);
    }

    @Override
    public void save(SysMenuEntity sysMenuEntity) {
        menuDao.save(sysMenuEntity);
    }

    @Override
    public void delete(SysMenuEntity sysMenuEntity) {
        menuDao.delete(sysMenuEntity);
    }

    @Override
    public void update(SysMenuEntity sysMenuEntity) {
        menuDao.update(sysMenuEntity);
    }

    @Override
    public SysMenuEntity findById(SysMenuEntity sysMenuEntity) {
        return menuDao.findById(sysMenuEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<SysMenuEntity> pageBean) {
        return menuDao.findByPage(key, pageBean);
    }

    @Override
    public List<SysMenuEntity> findAll() {
        return menuDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        menuDao.deleteBatch(Ids);
    }
}
