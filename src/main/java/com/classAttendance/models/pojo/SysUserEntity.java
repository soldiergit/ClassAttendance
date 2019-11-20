package com.classAttendance.models.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName:hibernateGenerator
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18上午10:30
 * @Describe:
 **/
@Entity
@Table(name = "sys_user", schema = "class_attendance", catalog = "")
public class SysUserEntity {
    private long id;
    private String loginNumber;     //账号
    private String loginPassword;   //密码
    private int userType;           //用户类型：1-管理员，2-老师，3-学生(默认)
    private String userName;        //用户名
    private int userStatus;         //0-删除，1-正常

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "login_number")
    public String getLoginNumber() {
        return loginNumber;
    }

    public void setLoginNumber(String loginNumber) {
        this.loginNumber = loginNumber;
    }

    @Basic
    @Column(name = "login_password")
    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Basic
    @Column(name = "user_type")
    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_status")
    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserEntity that = (SysUserEntity) o;
        return id == that.id &&
                userType == that.userType &&
                userStatus == that.userStatus &&
                Objects.equals(loginNumber, that.loginNumber) &&
                Objects.equals(loginPassword, that.loginPassword) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginNumber, loginPassword, userType, userName, userStatus);
    }
}
