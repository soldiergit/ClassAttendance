package com.classAttendance.models.sys.sys_user.action;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.common.vo.R;
import com.classAttendance.models.pojo.SysUserEntity;
import com.classAttendance.models.sys.sys_user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static com.classAttendance.common.DycContext.DEFUALT_PAGE_NUM;
import static com.classAttendance.common.DycContext.DEFUALT_PAGE_SIZE;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午3:23
 * @Describe:
 **/
public class UserAction extends ActionSupport implements ModelDriven<SysUserEntity> {

    @Autowired
    private UserService userService;
    private static Logger logger = Logger.getLogger(UserAction.class);
    //模型驱动
    private SysUserEntity sysUserEntity = new SysUserEntity();
    //查询条件
    private String key;
    //结果
    private R result;
    //当前页码 -当前页数
    private Integer page = DEFUALT_PAGE_NUM;
    //每页个数 -每页显示的记录数
    private Integer limit = DEFUALT_PAGE_SIZE;
    //批量处理
    private String userIds;

    @Override
    public SysUserEntity getModel() {
        return sysUserEntity;
    }

    /**
     * 添加
     */
    public String save(){

        logger.info("添加:"+ sysUserEntity);

        userService.save(sysUserEntity);

        this.result = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete(){

        logger.info("删除："+ sysUserEntity);

        userService.delete(sysUserEntity);

        result = R.ok();

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch(){

        logger.info("批量删除:"+ userIds);

        String[] ids = userIds.split(",");

        userService.deleteBatch(ids);

        result = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update(){

        logger.info("更新数据:"+ sysUserEntity);

        userService.update(sysUserEntity);

        result = R.ok();

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public SysUserEntity findById(){

        logger.info("查询:"+ sysUserEntity);

        sysUserEntity = userService.findById(this.sysUserEntity);


        return sysUserEntity;
    }

    /**
     * 查询
     */
    public String findByPage(){

        PageBean<SysUserEntity> pageBean = userService.findByPage(key,new PageBean<SysUserEntity>().setCurrPage(page).setPageSize(limit));

        this.result = R.ok().put("data",pageBean.getRows());

        logger.info("查询"+pageBean);

        return SUCCESS;

    }

    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
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

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
}
