package com.classAttendance.models.biz.tb_college.action;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.common.vo.R;
import com.classAttendance.models.biz.tb_college.service.CollegeService;
import com.classAttendance.models.pojo.TbCollegeEntity;
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
public class CollegeAction extends ActionSupport implements ModelDriven<TbCollegeEntity> {

    @Autowired
    private CollegeService collegeService;
    //日志
    private static Logger logger = Logger.getLogger(CollegeAction.class);
    //模型驱动
    private TbCollegeEntity tbCollegeEntity = new TbCollegeEntity();
    //分页
    private PageBean<TbCollegeEntity> pageBean = new PageBean<>();
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
    public TbCollegeEntity getModel() {
        return tbCollegeEntity;
    }

    /**
     * 添加
     */
    public String save() {

        logger.info("添加:" + tbCollegeEntity);

        collegeService.save(tbCollegeEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        logger.info("删除：" + tbCollegeEntity);

        collegeService.delete(tbCollegeEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        logger.info("批量删除:" + ids);

        String[] Ids = ids.split(",");

        collegeService.deleteBatch(Ids);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        logger.info("更新数据:" + tbCollegeEntity);

        collegeService.update(tbCollegeEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findById() {

        logger.info("查询:"+ tbCollegeEntity);

        TbCollegeEntity byId = collegeService.findById(tbCollegeEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 列表
     */
    public String findAll() {

        List<TbCollegeEntity> all = collegeService.findAll();

        logger.info("查询列表");

        r = R.ok().put("data", all);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = collegeService.findByPage(key, new PageBean<TbCollegeEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询" + r);

        return SUCCESS;

    }

    /////////////////////////////////////////

    public TbCollegeEntity getTbCollegeEntity() {
        return tbCollegeEntity;
    }

    public void setTbCollegeEntity(TbCollegeEntity tbCollegeEntity) {
        this.tbCollegeEntity = tbCollegeEntity;
    }

    public PageBean<TbCollegeEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<TbCollegeEntity> pageBean) {
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
