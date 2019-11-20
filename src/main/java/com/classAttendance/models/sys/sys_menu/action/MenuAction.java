package com.classAttendance.models.sys.sys_menu.action;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.common.vo.R;
import com.classAttendance.models.biz.tb_teacher.service.TeacherService;
import com.classAttendance.models.sys.sys_menu.service.MenuService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.classAttendance.models.pojo.SysMenuEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午3:23
 * @Describe:
 */
public class MenuAction extends ActionSupport implements ModelDriven<SysMenuEntity> {

    @Autowired
    private MenuService menuService;
    //日志
    private static Logger logger = Logger.getLogger(MenuAction.class);
    //模型驱动
    private SysMenuEntity sysMenuEntity = new SysMenuEntity();
    //分页
    private PageBean<SysMenuEntity> pageBean = new PageBean<>();
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

    @Override
    public SysMenuEntity getModel() {
        return sysMenuEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        sysMenuEntity.setSpread((byte) 0);

        menuService.save(sysMenuEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        menuService.delete(sysMenuEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        sysMenuEntity.setSpread((byte) 0);

        menuService.update(sysMenuEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById() {


        SysMenuEntity byId = menuService.findById(sysMenuEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = menuService.findByPage(key, new PageBean<SysMenuEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 查询全部
     */
    public String findAll() {

        List all = menuService.findAll();

        r = R.ok().put("data", all);

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        menuService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 获取菜单
     */
    public String findMenuByUser() {

        logger.info("userType：" + sysMenuEntity.getUserType());

        if (sysMenuEntity.getUserType()==null) return SUCCESS;

        List<SysMenuEntity> menuByUser = menuService.findMenuByUser(sysMenuEntity.getUserType());

        r = R.ok().put("contentManagement", menuByUser);

        logger.info("菜单信息：" + r);

        return SUCCESS;

    }

    /////////////////////////////////////////

    public SysMenuEntity getSysMenuEntity() {
        return sysMenuEntity;
    }

    public void setSysMenuEntity(SysMenuEntity sysMenuEntity) {
        this.sysMenuEntity = sysMenuEntity;
    }

    public PageBean<SysMenuEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<SysMenuEntity> pageBean) {
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
}
