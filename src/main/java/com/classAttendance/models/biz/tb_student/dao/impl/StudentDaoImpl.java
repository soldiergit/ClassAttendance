package com.classAttendance.models.biz.tb_student.dao.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.biz.tb_student.dao.StudentDao;
import com.classAttendance.models.pojo.TbStudentEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soldier
 * @title: StudentDaoImpl
 * @projectName ClassAttendance
 * @description: 学生
 * @date 19-6-2下午12:06
 */
@Component
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 新增学生
     * @param tbStudentEntity
     */
    @Override
    public void save(TbStudentEntity tbStudentEntity) {
        hibernateTemplate.save(tbStudentEntity);
    }

    /**
     * 删除学生
     * @param tbStudentEntity
     */
    @Override
    public void delete(TbStudentEntity tbStudentEntity) {
        hibernateTemplate.delete(tbStudentEntity);
    }

    /**
     * 修改学生
     * @param tbStudentEntity
     */
    @Override
    public void update(TbStudentEntity tbStudentEntity) {
        hibernateTemplate.update(tbStudentEntity);
    }

    /**
     * 条件查询
     * @param tbStudentEntity
     */
    @Override
    public TbStudentEntity findById(TbStudentEntity tbStudentEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbStudentEntity.class);

        List list = criteria.add(Restrictions.eq("id", tbStudentEntity.getId())).list();

        session.close();

        return list!=null&&list.size()>0? (TbStudentEntity) list.get(0) :null;

    }

    /**
     * 条件查询
     * @param tbStudentEntity
     */
    @Override
    public TbStudentEntity findByCode(TbStudentEntity tbStudentEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbStudentEntity.class);

        List list = criteria.add(Restrictions.eq("studentCode", tbStudentEntity.getStudentCode())).list();

        session.close();

        return list!=null&&list.size()>0? (TbStudentEntity) list.get(0) :null;
    }

    /**
     * 分页查询及条件查询
     * @param key
     * @param pageBean
     */
    @Override
    public PageBean findByPage(String key, PageBean<TbStudentEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbStudentEntity.class);

        if (key != null && key.length() != 0) {
            //搜索
            criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("studentName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("studentPhone", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("studentEmail", key, MatchMode.ANYWHERE))
                    )
            );
        }

        // 获取记录数
        pageBean.setTotal(Math.toIntExact((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()));

        // 获取结果--将 Projection 设为空，再进行正常分页
        criteria.setProjection(null);
        pageBean.setRows(criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());

        session.close();

        return pageBean;
    }

    @Override
    public List<TbStudentEntity> findAll(String key, String courseId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbStudentEntity.class);

        if (key != null && key.length() != 0) {
            //搜索
            criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("studentName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("studentPhone", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("studentEmail", key, MatchMode.ANYWHERE))
                    )
            );
        }

        if (courseId != null && courseId.length() != 0) {
            criteria.add(Restrictions.like("courseSet", courseId, MatchMode.ANYWHERE));
        }

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    /**
     * 批量删除
     * @param Ids
     */
    @Override
    public void deleteBatch(String[] Ids) {
        List<TbStudentEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TbStudentEntity tbStudentEntity = new TbStudentEntity();
            tbStudentEntity.setId(Long.parseLong(id));
            list.add(tbStudentEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
