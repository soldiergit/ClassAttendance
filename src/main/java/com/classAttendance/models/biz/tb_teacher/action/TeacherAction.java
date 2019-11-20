package com.classAttendance.models.biz.tb_teacher.action;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.common.vo.R;
import com.classAttendance.models.biz.tb_teacher.service.TeacherService;
import com.classAttendance.models.pojo.TbTeacherEntity;
import com.opensymphony.xwork2.ActionSupport;
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
public class TeacherAction extends ActionSupport implements ModelDriven<TbTeacherEntity> {

    @Autowired
    private TeacherService teacherService;
    //日志
    private static Logger logger = Logger.getLogger(TeacherAction.class);
    //模型驱动
    private TbTeacherEntity tbTeacherEntity = new TbTeacherEntity();
    //分页
    private PageBean<TbTeacherEntity> pageBean = new PageBean<>();
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
    // 所属学院
    private Long collegeId;

    @Override
    public TbTeacherEntity getModel() {
        return tbTeacherEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save(){

        if (teacherService.findByCode(tbTeacherEntity) != null) {
            logger.info("## 校验教师工号，出现重复！");
            r = R.error(444, "教师工号：『 "+ tbTeacherEntity.getTeacherCode() +" 』已存在，请重新输入！");
            return SUCCESS;
        }

        logger.info("添加:"+ tbTeacherEntity);

        teacherService.save(tbTeacherEntity, collegeId);

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete(){

        logger.info("删除："+ tbTeacherEntity);

        teacherService.delete(tbTeacherEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch(){

        logger.info("批量删除:"+ ids);

        String[] Ids = ids.split(",");

        teacherService.deleteBatch(Ids);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update(){

        logger.info("更新数据:"+ tbTeacherEntity);

        teacherService.update(tbTeacherEntity, collegeId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findById(){

        logger.info("查询:"+ tbTeacherEntity);

        TbTeacherEntity byId = teacherService.findById(tbTeacherEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 列表
     */
    public String findAll() {

        List<TbTeacherEntity> all = teacherService.findAll();

        logger.info("查询列表");

        r = R.ok().put("data", all);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage(){

        PageBean byPage = teacherService.findByPage(key, new PageBean<TbTeacherEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询" + r);

        return SUCCESS;

    }

    /////////////////////////////////////////

    public TbTeacherEntity getTbTeacherEntity() {
        return tbTeacherEntity;
    }
    public void setTbTeacherEntity(TbTeacherEntity tbTeacherEntity) {
        this.tbTeacherEntity = tbTeacherEntity;
    }

    public PageBean<TbTeacherEntity> getPageBean() {
        return pageBean;
    }
    public void setPageBean(PageBean<TbTeacherEntity> pageBean) {
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

    public Long getCollegeId() {
        return collegeId;
    }
    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }
}
