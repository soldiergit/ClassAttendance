package com.classAttendance.models.sys.sys_menu_children.service.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.SysMenuChildrenEntity;
import com.classAttendance.models.sys.sys_menu_children.dao.MenuChildrenDao;
import com.classAttendance.models.sys.sys_menu_children.service.MenuChildrenService;
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
public class MenuChildrenServiceImpl implements MenuChildrenService {

    @Autowired
    private MenuChildrenDao menuChildrenDao;

    @Override
    public void save(SysMenuChildrenEntity sysMenuChildrenEntity) {
        menuChildrenDao.save(sysMenuChildrenEntity);
    }

    @Override
    public void delete(SysMenuChildrenEntity sysMenuChildrenEntity) {
        menuChildrenDao.delete(sysMenuChildrenEntity);
    }

    @Override
    public void update(SysMenuChildrenEntity sysMenuChildrenEntity) {
        menuChildrenDao.update(sysMenuChildrenEntity);
    }

    @Override
    public SysMenuChildrenEntity findById(SysMenuChildrenEntity sysMenuChildrenEntity) {
        return menuChildrenDao.findById(sysMenuChildrenEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<SysMenuChildrenEntity> pageBean) {
        return menuChildrenDao.findByPage(key, pageBean);
    }

    @Override
    public List<SysMenuChildrenEntity> findAll() {
        return menuChildrenDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        menuChildrenDao.deleteBatch(Ids);
    }
}
