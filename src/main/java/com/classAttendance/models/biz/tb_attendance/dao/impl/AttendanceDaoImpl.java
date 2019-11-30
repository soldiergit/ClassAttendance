package com.classAttendance.models.biz.tb_attendance.dao.impl;

import com.classAttendance.common.vo.PageBean;
import com.classAttendance.models.biz.tb_attendance.dao.AttendanceDao;
import com.classAttendance.models.pojo.TbAttendanceEntity;
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
public class AttendanceDaoImpl implements AttendanceDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(TbAttendanceEntity tbAttendanceEntity) {
        hibernateTemplate.save(tbAttendanceEntity);
    }

    @Override
    public void delete(TbAttendanceEntity tbAttendanceEntity) {
        hibernateTemplate.delete(tbAttendanceEntity);

    }

    @Override
    public void update(TbAttendanceEntity tbAttendanceEntity) {
        hibernateTemplate.update(tbAttendanceEntity);

    }

    @Override
    public TbAttendanceEntity findById(TbAttendanceEntity tbAttendanceEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbAttendanceEntity.class);

        List list = criteria.add(Restrictions.eq("id", tbAttendanceEntity.getId())).list();

        session.close();

        return list!=null&&list.size()>0? (TbAttendanceEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, String teacherCode, String studentCode, PageBean<TbAttendanceEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbAttendanceEntity.class);

        if (key != null && key.length() != 0) {
            //搜索
            criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("courseName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("studentName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("attendanceTime", key, MatchMode.ANYWHERE))
                    )
            );
        }

        if (teacherCode != null && teacherCode.length() != 0) {
            criteria.add(Restrictions.eq("teacherCode", teacherCode));
        }
        if (studentCode != null && studentCode.length() != 0) {
            criteria.add(Restrictions.eq("studentCode", studentCode));
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
    public void deleteBatch(String[] Ids) {
        List<TbAttendanceEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TbAttendanceEntity tbAttendanceEntity = new TbAttendanceEntity();
            tbAttendanceEntity.setId(Long.parseLong(id));
            list.add(tbAttendanceEntity);
        }

        hibernateTemplate.deleteAll(list);
    }

    @Override
    public List<TbAttendanceEntity> findByExport(String teacherCode, String studentCode, String[] Ids) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TbAttendanceEntity.class);

        if (teacherCode != null && teacherCode.length() != 0) {
            criteria.add(Restrictions.eq("teacherCode", teacherCode));
        }

        if (studentCode != null && studentCode.length() != 0) {
            criteria.add(Restrictions.eq("studentCode", studentCode));
        }

        //是否根据id导出
        if (Ids!=null && Ids.length!=0) {
            //转Object数组
            Object[] ids = new Object[Ids.length];
            for(int i=0;i<Ids.length;i++){
                ids[i] = Long.valueOf(Ids[i]);
            }
            criteria.add(Restrictions.in("id", ids));
        }
        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}
