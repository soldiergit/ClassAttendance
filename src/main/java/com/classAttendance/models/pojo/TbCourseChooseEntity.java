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
@Table(name = "tb_course_choose", schema = "class_attendance", catalog = "")
public class TbCourseChooseEntity {
    private long id;
    /**
     * 单向一对一
     */
    private TbCourseEntity tbCourseEntity;  //课程
    private TbStudentEntity tbStudentEntity;//选课学生

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbCourseChooseEntity that = (TbCourseChooseEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * 这样子就可以实现单向一对一了，对方实体中什么都不用该，因为只是单向的
     */
    @OneToOne
    @JoinColumn(name = "course_id")
    public TbCourseEntity getTbCourseEntity() {
        return tbCourseEntity;
    }
    public void setTbCourseEntity(TbCourseEntity tbCourseEntity) {
        this.tbCourseEntity = tbCourseEntity;
    }

    @OneToOne
    @JoinColumn(name = "student_id")
    public TbStudentEntity getTbStudentEntity() {
        return tbStudentEntity;
    }
    public void setTbStudentEntity(TbStudentEntity tbStudentEntity) {
        this.tbStudentEntity = tbStudentEntity;
    }
}
