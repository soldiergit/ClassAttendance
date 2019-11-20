package com.classAttendance.models.sys.sys_user.dao.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.pojo.SysUserEntity;
import com.classAttendance.models.sys.sys_user.dao.UserDao;
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
 * @create:19-11-18下午3:26
 * @Describe:
 */
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 新增用户
     * @param sysUserEntity
     */
    @Override
    public void save(SysUserEntity sysUserEntity) {
        hibernateTemplate.save(sysUserEntity);
    }

    /**
     * 删除用户
     * @param sysUserEntity
     */
    @Override
    public void delete(SysUserEntity sysUserEntity) {
        hibernateTemplate.delete(sysUserEntity);
    }

    /**
     * 更新用户
     * @param sysUserEntity
     */
    @Override
    public void update(SysUserEntity sysUserEntity) {
        hibernateTemplate.update(sysUserEntity);
    }

    /**
     * 条件查询
     * @param sysUserEntity

     */
    @Override
    public SysUserEntity findById(SysUserEntity sysUserEntity) {

        Session currentSession = sessionFactory.openSession();

        Criteria criteria = currentSession.createCriteria(SysUserEntity.class);

        List<SysUserEntity> list = criteria.add(Restrictions.eq("userId", sysUserEntity.getId())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        currentSession.close();

        return list.size()>0&&list!=null?list.get(0):null;
    }

    @Override
    public SysUserEntity findByCode(SysUserEntity sysUserEntity) {

        Session currentSession = sessionFactory.openSession();

        Criteria criteria = currentSession.createCriteria(SysUserEntity.class);

        List<SysUserEntity> list = criteria.add(Restrictions.eq("loginNumber", sysUserEntity.getLoginNumber())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        currentSession.close();

        return list.size()>0&&list!=null?list.get(0):null;
    }

    /**
     * 分页查询及条件查询
     * @param key
     * @param pageBean
     */
    @Override
    public PageBean findByPage(String key, PageBean<SysUserEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysUserEntity.class);

        if (key != null && key.length() != 0) {
            criteria.add(Restrictions.or(
                    Restrictions.or(Restrictions.like("loginNumber", key, MatchMode.ANYWHERE)),
                    Restrictions.or(Restrictions.like("userName", key, MatchMode.ANYWHERE))));
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

    /**
     * 批量删除
     * @param userIds
     */
    @Override
    public void deleteBatch(String[] userIds) {
        List<SysUserEntity> list = new ArrayList<>();

        for (String id : userIds) {
            SysUserEntity sysUserEntity = new SysUserEntity();
            sysUserEntity.setId(Long.parseLong(id));
            list.add(sysUserEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
