package com.classAttendance.models.biz.tb_student.action;

import com.classAttendance.models.biz.tb_student.service.StudentService;
import com.classAttendance.models.pojo.TbStudentEntity;
import com.opensymphony.xwork2.ActionSupport;
import com.classAttendance.common.vo.PageBean;
import com.classAttendance.common.vo.R;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author soldier
 * @title: StudentAction
 * @projectName ClassAttendance
 * @description:
 * @date 19-6-2上午11:56
 */
public class StudentAction extends ActionSupport implements ModelDriven<TbStudentEntity> {

    @Autowired
    private StudentService studentService;
    //日志
    private static Logger logger = Logger.getLogger(StudentAction.class);
    //模型驱动
    private TbStudentEntity tbStudentEntity = new TbStudentEntity();
    //分页
    private PageBean<TbStudentEntity> pageBean = new PageBean<>();
    //返回集
    private R r = new R();
    //搜索值
    private String key;
    //当前页
    private Integer page;
    //大小
    private Integer limit;
    //批量删除id
    private String ids;
    // 所属班级
    private Long classId;
    //  课程id-默认########
    private String courseId = "########";

    @Override
    public TbStudentEntity getModel() {
        return tbStudentEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save(){

        if (studentService.findByCode(tbStudentEntity) != null) {
            logger.info("## 校验学生学号，出现重复！");
            r = R.error(444, "学生学号：『 "+ tbStudentEntity.getStudentCode() +" 』已存在，请重新输入！");
            return SUCCESS;
        }

        logger.info("添加:"+ tbStudentEntity);

        studentService.save(tbStudentEntity, classId);

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete(){

        logger.info("删除："+ tbStudentEntity);

        studentService.delete(tbStudentEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch(){

        logger.info("批量删除:"+ ids);

        String[] Ids = ids.split(",");

        studentService.deleteBatch(Ids);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update(){

        logger.info("更新数据:"+ tbStudentEntity);

        studentService.update(tbStudentEntity, classId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findById(){

        logger.info("查询:"+ tbStudentEntity);

        TbStudentEntity byId = studentService.findById(tbStudentEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 列表
     */
    public String findAll() {

        List<TbStudentEntity> all = studentService.findAll(null);

        logger.info("查询列表");

        r = R.ok().put("data", all);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage(){

        PageBean byPage = studentService.findByPage(key, new PageBean<TbStudentEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询" + r);

        return SUCCESS;

    }

    /**
     * 查询-任课教师
     */
    public String findByCourse(){

        List<TbStudentEntity> all = studentService.findAll(courseId);

        r = R.ok().put("data", all);

        logger.info("查询" + r);

        return SUCCESS;

    }

    /////////////////////////////////////////

    public TbStudentEntity getTbStudentEntity() {
        return tbStudentEntity;
    }
    public void setTbStudentEntity(TbStudentEntity tbStudentEntity) {
        this.tbStudentEntity = tbStudentEntity;
    }

    public PageBean<TbStudentEntity> getPageBean() {
        return pageBean;
    }
    public void setPageBean(PageBean<TbStudentEntity> pageBean) {
        this.pageBean = pageBean;
    }

    public R getR() {
        return r;
    }
    public void setR(R r) {
        this.r = r;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
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

    public String getIds() {
        return ids;
    }
    public void setIds(String ids) {
        this.ids = ids;
    }

    public Long getClassId() {
        return classId;
    }
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
