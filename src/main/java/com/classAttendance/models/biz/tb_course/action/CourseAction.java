package com.classAttendance.models.biz.tb_course.action;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.common.vo.R;
import com.classAttendance.models.biz.tb_course.service.CourseService;
import com.classAttendance.models.pojo.TbCourseEntity;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午4:52
 * @Describe:
 */
public class CourseAction extends ActionSupport implements ModelDriven<TbCourseEntity> {

    @Autowired
    private CourseService courseService;
    //日志
    private static Logger logger = Logger.getLogger(CourseAction.class);
    //模型驱动
    private TbCourseEntity tbCourseEntity = new TbCourseEntity();
    //分页
    private PageBean<TbCourseEntity> pageBean = new PageBean<>();
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
    // 开课老师
    private Long teacherId;
    private String teacherCode;
    //  选课学生
    private String studentCode;

    @Override
    public TbCourseEntity getModel() {
        return tbCourseEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        logger.info("添加:" + tbCourseEntity);

        courseService.save(tbCourseEntity, teacherId);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        logger.info("删除：" + tbCourseEntity);

        courseService.delete(tbCourseEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        logger.info("批量删除:" + ids);

        String[] Ids = ids.split(",");

        courseService.deleteBatch(Ids);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        logger.info("更新数据:" + tbCourseEntity);

        courseService.update(tbCourseEntity, teacherId);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findById() {

        logger.info("查询:"+ tbCourseEntity);

        TbCourseEntity byId = courseService.findById(tbCourseEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 列表
     */
    public String findAll() {

        List<TbCourseEntity> all = courseService.findAll(null);

        logger.info("查询列表");

        r = R.ok().put("data", all);

        return SUCCESS;
    }

    /**
     * 列表
     */
    public String findAllByTeacher() {

        List<TbCourseEntity> all = courseService.findAll(teacherCode);

        logger.info("查询列表");

        r = R.ok().put("data", all);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = courseService.findByPage(key, null, new PageBean<TbCourseEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询" + r);

        return SUCCESS;

    }

    /**
     * 查询
     */
    public String findByTeacher() {

        PageBean byPage = courseService.findByPage(key, teacherCode, new PageBean<TbCourseEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询" + r);

        return SUCCESS;

    }

    /**
     * 查询已选课--学生
     */
    public String findByHaveChosen() {

        PageBean byHaveChosen = courseService.findByHaveChosen(key, studentCode, new PageBean<TbCourseEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byHaveChosen.getRows()).put("count", byHaveChosen.getTotal());

        logger.info("查询" + r);

        return SUCCESS;

    }

    /**
     * 查询未选课--学生
     */
    public String findByNoChoice() {

        PageBean byNoChoice = courseService.findByNoChoice(key, studentCode, new PageBean<TbCourseEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byNoChoice.getRows()).put("count", byNoChoice.getTotal());

        logger.info("查询" + r);

        return SUCCESS;

    }

    /////////////////////////////////////////

    public TbCourseEntity getTbCourseEntity() {
        return tbCourseEntity;
    }
    public void setTbCourseEntity(TbCourseEntity tbCourseEntity) {
        this.tbCourseEntity = tbCourseEntity;
    }

    public PageBean<TbCourseEntity> getPageBean() {
        return pageBean;
    }
    public void setPageBean(PageBean<TbCourseEntity> pageBean) {
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

    public Long getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherCode() {
        return teacherCode;
    }
    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getStudentCode() {
        return studentCode;
    }
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
}
