package com.classAttendance.models.sys.login.dao.impl;

import com.classAttendance.common.vo.R;
import com.classAttendance.models.pojo.SysUserEntity;
import com.classAttendance.models.sys.login.dao.LoginDao;
import com.classAttendance.models.sys.sys_user.dao.UserDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午3:26
 * @Describe:
 */
@Component
public class LoginDaoImpl implements LoginDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserDao userDao;

    @Override
    public R userLogin(SysUserEntity sysUserEntity) {

        //打开回话
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysUserEntity.class);

        List list = criteria.add(
                Restrictions.eq("loginNumber", sysUserEntity.getLoginNumber()))
                .add(Restrictions.eq("loginPassword", sysUserEntity.getLoginPassword())).list();

        if (list==null||list.size()<1){

            Criteria criteria2 = session.createCriteria(SysUserEntity.class);

            List loginAccount = criteria2.add(Restrictions.eq("loginNumber", sysUserEntity.getLoginNumber())).list();

            if (loginAccount==null||loginAccount.size()<1){
                return R.error(1,"用户不存在");
            }
            return R.error(2,"密码错误");
        }

        session.close();

        return R.ok().put("data",list.get(0));
    }

    @Override
    public R updatePassword(SysUserEntity sysUserEntity, String newPassword) {

        SysUserEntity sysUserEntity_form_db = userDao.findById(sysUserEntity);

        if (sysUserEntity_form_db!=null && sysUserEntity.getLoginPassword().equals(sysUserEntity_form_db.getLoginPassword())) {
            sysUserEntity_form_db.setLoginPassword(newPassword);
            hibernateTemplate.update(sysUserEntity_form_db);
            return R.ok("修改成功");
        }

        return R.error(2,"原密码错误");
    }
}
