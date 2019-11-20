package com.classAttendance.models.sys.login.action;

import com.classAttendance.common.vo.R;
import com.classAttendance.models.pojo.SysUserEntity;
import com.classAttendance.models.sys.login.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午3:22
 * @Describe:
 **/
public class LoginAction extends ActionSupport implements ModelDriven<SysUserEntity> {
    @Autowired
    private LoginService loginService;
    //日志
    private static Logger logger = Logger.getLogger(LoginAction.class);
    //模型驱动
    private SysUserEntity sysUserEntity = new SysUserEntity();
    //返回集
    private R r = new R();
    //页面回显
    private String message;
    //修改密码
    private String newPassword;

    @Override
    public SysUserEntity getModel() {
        return sysUserEntity;
    }

    /**
     * 用户登陆

     */
    public String userLogin() {

        logger.info("###用户登陆");

        r = loginService.userLogin(sysUserEntity);

        return SUCCESS;
    }

    /**
     * 修改密码

     */
    public String updatePassword() {

        logger.info("修改密码："+sysUserEntity+"\t"+newPassword);
        r = loginService.updatePassword(sysUserEntity,newPassword);
        System.out.println(r);
        return SUCCESS;
    }

    public String saveUserInfo() {

        logger.info("保存用户信息到session对象");

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("userId", sysUserEntity.getId());
        session.setAttribute("userName", sysUserEntity.getUserName());
        session.setAttribute("userType", sysUserEntity.getUserType());

        return SUCCESS;
    }

    /**
     * 退出登陆

     */
    public String logout() {
        logger.info("###用户退出");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.invalidate();
        return SUCCESS;
    }

    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
