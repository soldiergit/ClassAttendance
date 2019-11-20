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
@Table(name = "tb_course", schema = "class_attendance", catalog = "")
public class TbCourseEntity {
    private long id;
    private String courseName;      //课程名称
    private String courseYear;      //学年
    private String courseTerm;      //学期
    private Integer courseHour;     //学时
    private Integer courseCapacity; //课程最大容量
    private Integer courseResidual; //剩余容量
    /**
     * 单向一对一
     */
    private TbTeacherEntity tbTeacherEntity;    //开课老师

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "course_name")
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "course_year")
    public String getCourseYear() {
        return courseYear;
    }
    public void setCourseYear(String courseYear) {
        this.courseYear = courseYear;
    }

    @Basic
    @Column(name = "course_term")
    public String getCourseTerm() {
        return courseTerm;
    }
    public void setCourseTerm(String courseTerm) {
        this.courseTerm = courseTerm;
    }

    @Basic
    @Column(name = "course_hour")
    public Integer getCourseHour() {
        return courseHour;
    }
    public void setCourseHour(Integer courseHour) {
        this.courseHour = courseHour;
    }

    @Basic
    @Column(name = "course_capacity")
    public Integer getCourseCapacity() {
        return courseCapacity;
    }
    public void setCourseCapacity(Integer courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    @Basic
    @Column(name = "course_residual")
    public Integer getCourseResidual() {
        return courseResidual;
    }
    public void setCourseResidual(Integer courseResidual) {
        this.courseResidual = courseResidual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbCourseEntity that = (TbCourseEntity) o;
        return id == that.id &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(courseYear, that.courseYear) &&
                Objects.equals(courseTerm, that.courseTerm) &&
                Objects.equals(courseHour, that.courseHour) &&
                Objects.equals(courseCapacity, that.courseCapacity) &&
                Objects.equals(courseResidual, that.courseResidual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, courseYear, courseTerm, courseHour, courseCapacity, courseResidual);
    }

    /**
     * 这样子就可以实现单向一对一了，对方实体中什么都不用该，因为只是单向的
     */
    @OneToOne
    @JoinColumn(name = "teacher_id")
    public TbTeacherEntity getTbTeacherEntity() {
        return tbTeacherEntity;
    }

    public void setTbTeacherEntity(TbTeacherEntity tbTeacherEntity) {
        this.tbTeacherEntity = tbTeacherEntity;
    }
}
