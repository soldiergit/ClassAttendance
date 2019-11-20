package com.classAttendance.models.biz.tb_college.service.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.biz.tb_college.dao.CollegeDao;
import com.classAttendance.models.biz.tb_college.service.CollegeService;
import com.classAttendance.models.pojo.TbCollegeEntity;
import com.classAttendance.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: CollegeDaoImpl
 @ProjectName:ClassAttendance
 * @description: 学院
 * @date 19-6-4上午9:04
 */
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeDao collegeDao;

    @Override
    public void save(TbCollegeEntity tbCollegeEntity) {
        tbCollegeEntity.setId(IDUtils.genItemId());
        collegeDao.save(tbCollegeEntity);
    }

    @Override
    public void delete(TbCollegeEntity tbCollegeEntity) {
        collegeDao.delete(tbCollegeEntity);
    }

    @Override
    public void update(TbCollegeEntity tbCollegeEntity) {
        collegeDao.update(tbCollegeEntity);
    }

    @Override
    public TbCollegeEntity findById(TbCollegeEntity tbCollegeEntity) {
        return collegeDao.findById(tbCollegeEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TbCollegeEntity> pageBean) {
        return collegeDao.findByPage(key,pageBean);
    }

    @Override
    public List<TbCollegeEntity> findAll() {
        return collegeDao.findAll();
    }

    @Override
    public void deleteBatch(String[] collegeIds) {
        collegeDao.deleteBatch(collegeIds);
    }
}
