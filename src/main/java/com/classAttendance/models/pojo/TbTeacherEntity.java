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
@Table(name = "tb_teacher", schema = "class_attendance", catalog = "")
public class TbTeacherEntity {
    private long id;
    private String teacherCode; //工号
    private String teacherName; //姓名
    private Integer teacherSex; //性别：0-女，1-男(默认)
    /**
     * 单向一对一
     */
    private TbCollegeEntity tbCollegeEntity;  //学院

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "teacher_code")
    public String getTeacherCode() {
        return teacherCode;
    }
    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    @Basic
    @Column(name = "teacher_name")
    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Basic
    @Column(name = "teacher_sex")
    public Integer getTeacherSex() {
        return teacherSex;
    }
    public void setTeacherSex(Integer teacherSex) {
        this.teacherSex = teacherSex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbTeacherEntity that = (TbTeacherEntity) o;
        return id == that.id &&
                Objects.equals(teacherCode, that.teacherCode) &&
                Objects.equals(teacherName, that.teacherName) &&
                Objects.equals(teacherSex, that.teacherSex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacherCode, teacherName, teacherSex);
    }

    /**
     * 这样子就可以实现单向一对一了，对方实体中什么都不用该，因为只是单向的
     */
    @OneToOne
    @JoinColumn(name = "college_id")
    public TbCollegeEntity getTbCollegeEntity() {
        return tbCollegeEntity;
    }
    public void setTbCollegeEntity(TbCollegeEntity tbCollegeEntity) {
        this.tbCollegeEntity = tbCollegeEntity;
    }
}
