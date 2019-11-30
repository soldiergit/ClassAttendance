package com.classAttendance.models.biz.tb_attendance.action;

import com.alibaba.fastjson.JSON;
import com.classAttendance.common.vo.PageBean;
import com.classAttendance.common.vo.R;
import com.classAttendance.models.biz.tb_attendance.service.AttendanceService;
import com.classAttendance.models.pojo.CheckWorkModel;
import com.classAttendance.models.pojo.TbAttendanceEntity;
import com.classAttendance.utils.DateUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午4:52
 * @Describe:
 */
public class AttendanceAction extends ActionSupport implements ModelDriven<TbAttendanceEntity> {

    @Autowired
    private AttendanceService attendanceService;
    //日志
    private static Logger logger = Logger.getLogger(AttendanceAction.class);
    //模型驱动
    private TbAttendanceEntity tbAttendanceEntity = new TbAttendanceEntity();
    //分页
    private PageBean<TbAttendanceEntity> pageBean = new PageBean<>();
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
    // 被考勤学生
    private String checkWorkStu;
    // 1-管理员导出全部， 2-老师导出全部， 3-学生导出全部， 4-根据id批量导出
    private Integer exportCode;

    @Override
    public TbAttendanceEntity getModel() {
        return tbAttendanceEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        logger.info("添加:" + tbAttendanceEntity);

        attendanceService.save(tbAttendanceEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 教师点名
     */
    public String checkWork() {

        // 转化为实体类对象
        List<CheckWorkModel> checkWorkModels = JSON.parseArray(checkWorkStu, CheckWorkModel.class);
        tbAttendanceEntity.setAttendanceTime(DateUtil.date2StringSimple(new Date()));
        TbAttendanceEntity entity;
        for (CheckWorkModel model : checkWorkModels) {
            entity = new TbAttendanceEntity();
            entity.setCourseId(tbAttendanceEntity.getCourseId());
            entity.setCourseName(tbAttendanceEntity.getCourseName());
            entity.setTeacherCode(tbAttendanceEntity.getTeacherCode());
            entity.setAttendanceTime(tbAttendanceEntity.getAttendanceTime());
            entity.setStudentCode(model.getStudentCode());
            entity.setStudentName(model.getStudentName());
            entity.setAttendanceType(model.getAttendanceType());
            attendanceService.save(entity);
        }

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        logger.info("删除：" + tbAttendanceEntity);

        attendanceService.delete(tbAttendanceEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        logger.info("批量删除：" + ids);

        String[] Ids = ids.split(",");

        attendanceService.deleteBatch(Ids);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        logger.info("更新数据:" + tbAttendanceEntity);

        attendanceService.update(tbAttendanceEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findById() {

        logger.info("查询:"+ tbAttendanceEntity);

        TbAttendanceEntity byId = attendanceService.findById(tbAttendanceEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = attendanceService.findByPage(key, null, null, new PageBean<TbAttendanceEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询" + r);

        return SUCCESS;

    }

    /**
     * 查询--教师
     */
    public String findByTea() {

        PageBean byTea = attendanceService.findByPage(key, tbAttendanceEntity.getTeacherCode(), null, new PageBean<TbAttendanceEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byTea.getRows()).put("count", byTea.getTotal());

        logger.info("查询" + r);

        return SUCCESS;

    }

    /**
     * 查询--学生
     */
    public String findByStu() {

        PageBean byStu = attendanceService.findByPage(key, null, tbAttendanceEntity.getStudentCode(), new PageBean<TbAttendanceEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byStu.getRows()).put("count", byStu.getTotal());

        logger.info("查询" + r);

        return SUCCESS;

    }

    /**
     * 导出
     *  1-管理员导出全部， 2-老师导出全部， 3-学生导出全部， 4-根据id批量导出
     */
    public String findByExport() {
        List<TbAttendanceEntity> byExport = new ArrayList<>();
        if (exportCode.equals(1)) {
            byExport = attendanceService.findByExport(null, null, null);
        } else if (exportCode.equals(2)) {
            byExport = attendanceService.findByExport(tbAttendanceEntity.getTeacherCode(), null, null);
        } else if (exportCode.equals(3)) {
            byExport = attendanceService.findByExport(null, tbAttendanceEntity.getStudentCode(), null);
        } else if (exportCode.equals(4)) {
            byExport = attendanceService.findByExport(null, null, ids.split(","));
        }

        r = R.ok().put("data", byExport);

        logger.info("导出" + r);

        return SUCCESS;

    }

    /////////////////////////////////////////

    public TbAttendanceEntity getTbAttendanceEntity() {
        return tbAttendanceEntity;
    }

    public void setTbAttendanceEntity(TbAttendanceEntity tbAttendanceEntity) {
        this.tbAttendanceEntity = tbAttendanceEntity;
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

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public PageBean<TbAttendanceEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<TbAttendanceEntity> pageBean) {
        this.pageBean = pageBean;
    }

    public String getCheckWorkStu() {
        return checkWorkStu;
    }

    public void setCheckWorkStu(String checkWorkStu) {
        this.checkWorkStu = checkWorkStu;
    }

    public Integer getExportCode() {
        return exportCode;
    }

    public void setExportCode(Integer exportCode) {
        this.exportCode = exportCode;
    }
}
