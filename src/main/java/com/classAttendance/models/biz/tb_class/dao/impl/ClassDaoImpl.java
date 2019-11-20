package com.classAttendance.models.biz.tb_class.dao.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.biz.tb_class.dao.ClassDao;
import com.classAttendance.models.pojo.TbClassEntity;
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
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午3:35
 * @Describe:
 **/
@Component
public class ClassDaoImpl implements ClassDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(TbClassEntity tbClassEntity) {
        hibernateTemplate.save(tbClassEntity);
    }

    @Override
    public void delete(TbClassEntity tbClassEntity) {
        hibernateTemplate.delete(tbClassEntity);

    }

    @Override
    public void update(TbClassEntity tbClassEntity) {
        hibernateTemplate.update(tbClassEntity);

    }

    @Override
    public TbClassEntity findById(TbClassEntity tbClassEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbClassEntity.class);

        List list = criteria.add(Restrictions.eq("id", tbClassEntity.getId())).list();

        session.close();

        return list!=null&&list.size()>0? (TbClassEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<TbClassEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbClassEntity.class);

        if (key != null && key.length() != 0) {
            //搜索
            criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("className", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("classGrade", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("classMajor", key, MatchMode.ANYWHERE))
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
    public List<TbClassEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbClassEntity.class);

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<TbClassEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TbClassEntity tbClassEntity = new TbClassEntity();
            tbClassEntity.setId(Long.parseLong(id));
            list.add(tbClassEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
