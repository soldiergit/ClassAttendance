package com.classAttendance.models.biz.tb_teacher.dao.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.biz.tb_teacher.dao.TeacherDao;
import com.classAttendance.models.pojo.TbTeacherEntity;
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
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 新增学生
     * @param tbTeacherEntity
     */
    @Override
    public void save(TbTeacherEntity tbTeacherEntity) {
        hibernateTemplate.save(tbTeacherEntity);
    }

    /**
     * 删除学生
     * @param tbTeacherEntity
     */
    @Override
    public void delete(TbTeacherEntity tbTeacherEntity) {
        hibernateTemplate.delete(tbTeacherEntity);
    }

    /**
     * 修改学生
     * @param tbTeacherEntity
     */
    @Override
    public void update(TbTeacherEntity tbTeacherEntity) {
        hibernateTemplate.update(tbTeacherEntity);
    }

    /**
     * 条件查询
     * @param tbTeacherEntity
     */
    @Override
    public TbTeacherEntity findById(TbTeacherEntity tbTeacherEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbTeacherEntity.class);

        List list = criteria.add(Restrictions.eq("id", tbTeacherEntity.getId())).list();

        session.close();

        return list!=null&&list.size()>0? (TbTeacherEntity) list.get(0) :null;

    }

    @Override
    public TbTeacherEntity findByCode(TbTeacherEntity tbTeacherEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbTeacherEntity.class);

        List list = criteria.add(Restrictions.eq("teacherCode", tbTeacherEntity.getTeacherCode())).list();

        session.close();

        return list!=null&&list.size()>0? (TbTeacherEntity) list.get(0) :null;
    }

    /**
     * 分页查询及条件查询
     * @param key
     * @param pageBean
     */
    @Override
    public PageBean findByPage(String key, PageBean<TbTeacherEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbTeacherEntity.class);

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
    public List<TbTeacherEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbTeacherEntity.class);

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {
        List<TbTeacherEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TbTeacherEntity tbTeacherEntity = new TbTeacherEntity();
            tbTeacherEntity.setId(Long.parseLong(id));
            list.add(tbTeacherEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
