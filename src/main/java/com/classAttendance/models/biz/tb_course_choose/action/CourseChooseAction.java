package com.classAttendance.models.biz.tb_course_choose.action;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.common.vo.R;
import com.classAttendance.models.biz.tb_course_choose.service.CourseChooseService;
import com.classAttendance.models.pojo.CourseChooseModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午4:52
 * @Describe:
 */
public class CourseChooseAction extends ActionSupport implements ModelDriven<CourseChooseModel> {

    @Autowired
    private CourseChooseService courseChooseService;
    //日志
    private static Logger logger = Logger.getLogger(CourseChooseAction.class);
    //模型驱动
    private CourseChooseModel courseChooseModel = new CourseChooseModel();
    //分页
    private PageBean<CourseChooseModel> pageBean = new PageBean<>();
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

    /////////////////////////////////////////

    @Override
    public CourseChooseModel getModel() {
        return courseChooseModel;
    }

    /////////////////////////////////////////

    public CourseChooseModel getCourseChooseModel() {
        return courseChooseModel;
    }

    public void setCourseChooseModel(CourseChooseModel courseChooseModel) {
        this.courseChooseModel = courseChooseModel;
    }

    public PageBean<CourseChooseModel> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<CourseChooseModel> pageBean) {
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
}
