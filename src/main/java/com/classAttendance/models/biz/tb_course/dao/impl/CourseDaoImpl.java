package com.classAttendance.models.biz.tb_course.dao.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.biz.tb_course.dao.CourseDao;
import com.classAttendance.models.pojo.TbCourseEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18上午10:57
 * @Describe:
 */
@Repository
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(TbCourseEntity tbCourseEntity) {
        hibernateTemplate.save(tbCourseEntity);
    }

    @Override
    public void delete(TbCourseEntity tbCourseEntity) {
        hibernateTemplate.delete(tbCourseEntity);

    }

    @Override
    public void update(TbCourseEntity tbCourseEntity) {
        hibernateTemplate.update(tbCourseEntity);

    }

    @Override
    public TbCourseEntity findById(TbCourseEntity tbCourseEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbCourseEntity.class);

        List list = criteria.add(Restrictions.eq("id", tbCourseEntity.getId())).list();

        session.close();

        return list!=null&&list.size()>0? (TbCourseEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, String teacherCode, PageBean<TbCourseEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbCourseEntity.class);

        if (key != null && key.length() != 0) {
            //搜索
            criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("courseName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("courseYear", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("courseTerm", key, MatchMode.ANYWHERE))
                    )
            );
        }

        if (teacherCode != null && teacherCode.length() != 0) {
            // 外键实体属性查询，不是外键id
            criteria.createAlias("tbTeacherEntity", "tbTeacherEntity")
                    .add(Restrictions.eq("tbTeacherEntity.teacherCode", teacherCode));
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
    public List<TbCourseEntity> findAll(String teacherCode) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbCourseEntity.class);

        if (teacherCode != null && teacherCode.length() != 0) {
            // 外键实体属性查询，不是外键id
            criteria.createAlias("tbTeacherEntity", "tbTeacherEntity")
                    .add(Restrictions.eq("tbTeacherEntity.teacherCode", teacherCode));
        }

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<TbCourseEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TbCourseEntity tbCourseEntity = new TbCourseEntity();
            tbCourseEntity.setId(Long.parseLong(id));
            list.add(tbCourseEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
