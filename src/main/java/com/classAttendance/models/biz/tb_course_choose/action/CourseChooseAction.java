package com.classAttendance.models.biz.tb_course_choose.action;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.common.vo.R;
import com.classAttendance.models.biz.tb_course_choose.service.CourseChooseService;
import com.classAttendance.models.pojo.TbCourseChooseEntity;
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
public class CourseChooseAction extends ActionSupport implements ModelDriven<TbCourseChooseEntity> {

    @Autowired
    private CourseChooseService courseChooseService;
    //日志
    private static Logger logger = Logger.getLogger(CourseChooseAction.class);
    //模型驱动
    private TbCourseChooseEntity tbCourseChooseEntity = new TbCourseChooseEntity();
    //分页
    private PageBean<TbCourseChooseEntity> pageBean = new PageBean<>();
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
    public TbCourseChooseEntity getModel() {
        return tbCourseChooseEntity;
    }

    /**
     * 添加
     */
    public String save() {

        logger.info("添加:" + tbCourseChooseEntity);

        courseChooseService.save(tbCourseChooseEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        logger.info("删除：" + tbCourseChooseEntity);

        courseChooseService.delete(tbCourseChooseEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        logger.info("批量删除:" + ids);

        String[] Ids = ids.split(",");

        courseChooseService.deleteBatch(Ids);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        logger.info("更新数据:" + tbCourseChooseEntity);

        courseChooseService.update(tbCourseChooseEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findById() {

        logger.info("查询:"+ tbCourseChooseEntity);

        TbCourseChooseEntity byId = courseChooseService.findById(tbCourseChooseEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = courseChooseService.findByPage(key, new PageBean<TbCourseChooseEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询" + r);

        return SUCCESS;

    }

    /////////////////////////////////////////

    public TbCourseChooseEntity getTbCourseChooseEntity() {
        return tbCourseChooseEntity;
    }

    public void setTbCourseChooseEntity(TbCourseChooseEntity tbCourseChooseEntity) {
        this.tbCourseChooseEntity = tbCourseChooseEntity;
    }

    public PageBean<TbCourseChooseEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<TbCourseChooseEntity> pageBean) {
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
