package com.classAttendance.models.biz.tb_college.dao.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.biz.tb_college.dao.CollegeDao;
import com.classAttendance.models.pojo.TbCollegeEntity;
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
public class CollegeDaoImpl implements CollegeDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(TbCollegeEntity tbCollegeEntity) {
        hibernateTemplate.save(tbCollegeEntity);
    }

    @Override
    public void delete(TbCollegeEntity tbCollegeEntity) {
        hibernateTemplate.delete(tbCollegeEntity);

    }

    @Override
    public void update(TbCollegeEntity tbCollegeEntity) {
        hibernateTemplate.update(tbCollegeEntity);

    }

    @Override
    public TbCollegeEntity findById(TbCollegeEntity tbCollegeEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbCollegeEntity.class);

        List list = criteria.add(Restrictions.eq("id", tbCollegeEntity.getId())).list();

        session.close();

        return list!=null&&list.size()>0? (TbCollegeEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<TbCollegeEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbCollegeEntity.class);

        if (key != null && key.length() != 0) {
            //搜索
            criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("collegeName", key, MatchMode.ANYWHERE))
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
    public List<TbCollegeEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbCollegeEntity.class);

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<TbCollegeEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TbCollegeEntity tbCollegeEntity = new TbCollegeEntity();
            tbCollegeEntity.setId(Long.parseLong(id));
            list.add(tbCollegeEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
