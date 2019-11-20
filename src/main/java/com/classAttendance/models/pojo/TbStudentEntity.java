package com.classAttendance.models.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName:hibernateGenerator
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18上午10:31
 * @Describe:
 **/
@Entity
@Table(name = "tb_student", schema = "class_attendance", catalog = "")
public class TbStudentEntity {
    private long id;
    private String studentCode; //学号
    private String studentName; //姓名
    private Integer studentSex; //性别：0-女，1-男(默认)
    private String studentPhone;//电话
    private String studentEmail;//邮箱
    private String courseSet;   //课程
    private String checkWorkType;   //无用字段，用于数据表格显示出勤状态
    /**
     * 单向一对一
     */
    private TbClassEntity tbClassEntity;    //所属班级

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "student_code")
    public String getStudentCode() {
        return studentCode;
    }
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    @Basic
    @Column(name = "student_name")
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "student_sex")
    public Integer getStudentSex() {
        return studentSex;
    }
    public void setStudentSex(Integer studentSex) {
        this.studentSex = studentSex;
    }

    @Basic
    @Column(name = "student_phone")
    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    @Basic
    @Column(name = "student_email")
    public String getStudentEmail() {
        return studentEmail;
    }
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    @Basic
    @Column(name = "course_set")
    public String getCourseSet() {
        return courseSet;
    }
    public void setCourseSet(String courseSet) {
        this.courseSet = courseSet;
    }

    public String getCheckWorkType() {
        return checkWorkType;
    }

    public void setCheckWorkType(String checkWorkType) {
        this.checkWorkType = checkWorkType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbStudentEntity that = (TbStudentEntity) o;
        return id == that.id &&
                Objects.equals(studentCode, that.studentCode) &&
                Objects.equals(studentName, that.studentName) &&
                Objects.equals(studentSex, that.studentSex) &&
                Objects.equals(studentPhone, that.studentPhone) &&
                Objects.equals(studentEmail, that.studentEmail) &&
                Objects.equals(courseSet, that.courseSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentCode, studentName, studentSex, studentPhone, studentEmail, courseSet);
    }

    /**
     * 这样子就可以实现单向一对一了，对方实体中什么都不用该，因为只是单向的
     */
    @OneToOne
    @JoinColumn(name = "class_id")
    public TbClassEntity getTbClassEntity() {
        return tbClassEntity;
    }
    public void setTbClassEntity(TbClassEntity tbClassEntity) {
        this.tbClassEntity = tbClassEntity;
    }
}
