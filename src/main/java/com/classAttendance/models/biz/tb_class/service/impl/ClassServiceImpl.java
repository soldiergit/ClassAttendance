package com.classAttendance.models.biz.tb_class.service.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.biz.tb_class.dao.ClassDao;
import com.classAttendance.models.biz.tb_class.service.ClassService;
import com.classAttendance.models.biz.tb_college.service.CollegeService;
import com.classAttendance.models.pojo.TbClassEntity;
import com.classAttendance.models.pojo.TbCollegeEntity;
import com.classAttendance.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午3:43
 * @Describe:
 **/
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassDao classDao;
    @Autowired
    private CollegeService collegeService;

    @Override
    public void save(TbClassEntity tbClassEntity, Long collegeId) {

        //所属学院
        TbCollegeEntity collegeEntity_result = new TbCollegeEntity();
        collegeEntity_result.setId(collegeId);
        collegeEntity_result = collegeService.findById(collegeEntity_result);
        tbClassEntity.setTbCollegeEntity(collegeEntity_result);

        tbClassEntity.setId(IDUtils.genItemId());
        classDao.save(tbClassEntity);
    }

    @Override
    public void delete(TbClassEntity tbClassEntity) {
        classDao.delete(tbClassEntity);
    }

    @Override
    public void update(TbClassEntity tbClassEntity, Long collegeId) {

        //所属学院
        TbCollegeEntity collegeEntity_result = new TbCollegeEntity();
        collegeEntity_result.setId(collegeId);
        collegeEntity_result = collegeService.findById(collegeEntity_result);
        tbClassEntity.setTbCollegeEntity(collegeEntity_result);

        classDao.update(tbClassEntity);
    }

    @Override
    public TbClassEntity findById(TbClassEntity tbClassEntity) {
        return classDao.findById(tbClassEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TbClassEntity> pageBean) {
        return classDao.findByPage(key, pageBean);
    }

    @Override
    public List<TbClassEntity> findAll() {
        return classDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        classDao.deleteBatch(Ids);
    }
}
