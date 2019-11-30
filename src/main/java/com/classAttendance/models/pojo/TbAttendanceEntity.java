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
@Table(name = "tb_attendance", schema = "class_attendance", catalog = "")
public class TbAttendanceEntity {
    private long id;
    private Long courseId;
    private String courseName;      //考勤课程
    private String studentCode;     //学号
    private String studentName;     //被考勤学生
    private String attendanceTime;  //考勤时间
    private Integer attendanceType; //考勤情况：1-出勤，2-请假，3-迟到，4-旷课
    private String teacherCode;     //考勤老师工号

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "course_id")
    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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
    @Column(name = "attendance_time")
    public String getAttendanceTime() {
        return attendanceTime;
    }
    public void setAttendanceTime(String attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    @Basic
    @Column(name = "attendance_type")
    public Integer getAttendanceType() {
        return attendanceType;
    }
    public void setAttendanceType(Integer attendanceType) {
        this.attendanceType = attendanceType;
    }

    @Basic
    @Column(name = "teacher_code")
    public String getTeacherCode() {
        return teacherCode;
    }
    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbAttendanceEntity that = (TbAttendanceEntity) o;
        return id == that.id &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(studentCode, that.studentCode) &&
                Objects.equals(studentName, that.studentName) &&
                Objects.equals(attendanceTime, that.attendanceTime) &&
                Objects.equals(attendanceType, that.attendanceType) &&
                Objects.equals(teacherCode, that.teacherCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, courseName, studentCode, studentName, attendanceTime, attendanceType, teacherCode);
    }
}
