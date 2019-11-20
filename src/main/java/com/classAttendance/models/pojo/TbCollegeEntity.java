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
@Table(name = "tb_college", schema = "class_attendance", catalog = "")
public class TbCollegeEntity {
    private long id;
    private String collegeName; //学院名称

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "college_name")
    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbCollegeEntity that = (TbCollegeEntity) o;
        return id == that.id &&
                Objects.equals(collegeName, that.collegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, collegeName);
    }
}
