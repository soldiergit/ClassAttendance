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
@Table(name = "tb_class", schema = "class_attendance", catalog = "")
public class TbClassEntity {
    private long id;
    private String className;   //班级名称
    private String classGrade;  //年级名称
    private String classMajor;  //专业名称
    /**
     * 单向一对一
     */
    private TbCollegeEntity tbCollegeEntity;    //学院名称

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "class_name")
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "class_grade")
    public String getClassGrade() {
        return classGrade;
    }
    public void setClassGrade(String classGrade) {
        this.classGrade = classGrade;
    }

    @Basic
    @Column(name = "class_major")
    public String getClassMajor() {
        return classMajor;
    }
    public void setClassMajor(String classMajor) {
        this.classMajor = classMajor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbClassEntity that = (TbClassEntity) o;
        return id == that.id &&
                Objects.equals(className, that.className) &&
                Objects.equals(classGrade, that.classGrade) &&
                Objects.equals(classMajor, that.classMajor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, className, classGrade, classMajor);
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
