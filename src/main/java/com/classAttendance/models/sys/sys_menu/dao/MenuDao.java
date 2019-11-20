package com.classAttendance.models.sys.sys_menu.dao;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.SysMenuEntity;

import java.util.List;

/**
 * @author soldier
 * @title: MenuDao
 * @projectName class_attendance
 * @date 19-7-5上午10:50
 */
public interface MenuDao {

    /**
     * 添加
     */
    public void save(SysMenuEntity sysMenuEntity);

    /**
     * 删除
     */
    public void delete(SysMenuEntity sysMenuEntity);

    /**
     * 修改
     */
    public void update(SysMenuEntity sysMenuEntity);

    /**
     * 查询
     */
    public SysMenuEntity findById(SysMenuEntity sysMenuEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<SysMenuEntity> pageBean);

    /**
     * 查询全部
     * @return
     */
    public List<SysMenuEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

    /**
     * 用户登陆获取菜单
     * @param userType
     */
    List<SysMenuEntity> findMenuByUser(Integer userType);
}
