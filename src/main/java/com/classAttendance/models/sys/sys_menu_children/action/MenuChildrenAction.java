package com.classAttendance.models.sys.sys_menu_children.action;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.common.vo.R;
import com.classAttendance.models.pojo.SysMenuChildrenEntity;
import com.classAttendance.models.pojo.SysMenuEntity;
import com.classAttendance.models.sys.sys_menu.service.MenuService;
import com.classAttendance.models.sys.sys_menu_children.service.MenuChildrenService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author soldier
 * @title: MenuAction
 * @projectName class_attendance
 * @date 19-7-5下午6:00
 * @Email： 583403411@qq.com
 * @description:
 */
public class MenuChildrenAction extends ActionSupport implements ModelDriven<SysMenuChildrenEntity> {

    @Autowired
    private MenuChildrenService menuChildrenService;
    @Autowired
    private MenuService menuService;
    //日志
    private static Logger logger = Logger.getLogger(MenuChildrenAction.class);
    //模型驱动
    private SysMenuChildrenEntity sysMenuChildrenEntity = new SysMenuChildrenEntity();
    //分页
    private PageBean<SysMenuChildrenEntity> pageBean = new PageBean<>();
    //返回集
    private R r = new R();
    //搜索值
    private String key;
    //当前页
    private Integer page;
    //大小
    private Integer limit;
    //批量删除id
    private String ids;
    //教师的所有角色id
    private String parentId;

    @Override
    public SysMenuChildrenEntity getModel() {
        return sysMenuChildrenEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        //获取父级菜单信息
        SysMenuEntity sysMenuEntity_result = new SysMenuEntity();
        sysMenuEntity_result.setMenuId(Integer.valueOf(parentId));
        sysMenuEntity_result = menuService.findById(sysMenuEntity_result);
        sysMenuChildrenEntity.setMenu(sysMenuEntity_result);

        sysMenuChildrenEntity.setSpread((byte) 0);

        menuChildrenService.save(sysMenuChildrenEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        menuChildrenService.delete(sysMenuChildrenEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        //获取父级菜单信息
        SysMenuEntity sysMenuEntity_result = new SysMenuEntity();
        sysMenuEntity_result.setMenuId(Integer.valueOf(parentId));
        sysMenuEntity_result = menuService.findById(sysMenuEntity_result);
        sysMenuChildrenEntity.setMenu(sysMenuEntity_result);

        sysMenuChildrenEntity.setSpread((byte) 0);

        menuChildrenService.update(sysMenuChildrenEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById() {


        SysMenuChildrenEntity byId = menuChildrenService.findById(sysMenuChildrenEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = menuChildrenService.findByPage(key, new PageBean<SysMenuChildrenEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 查询全部
     */
    public String findAll() {

        List all = menuChildrenService.findAll();

        r = R.ok().put("data", all);

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        menuChildrenService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////

    public SysMenuChildrenEntity getSysMenuChildrenEntity() {
        return sysMenuChildrenEntity;
    }

    public void setSysMenuChildrenEntity(SysMenuChildrenEntity sysMenuChildrenEntity) {
        this.sysMenuChildrenEntity = sysMenuChildrenEntity;
    }

    public PageBean<SysMenuChildrenEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<SysMenuChildrenEntity> pageBean) {
        this.pageBean = pageBean;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
