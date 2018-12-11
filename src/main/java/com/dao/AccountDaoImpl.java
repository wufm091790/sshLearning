package com.dao;

import com.domain.Account;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /** 
    * @Description: 转账
    * @Param:  out 转出的账号
    * @return:  money 转出的钱
    * @Author: wfm 
    * @Date: 2018/11/15 
    */ 
    public void outMoney(String out, Double money) {
        String sql1 = "select money from Account a where a.name = :out";
        Session session = sessionFactory.getCurrentSession();
        Query query1 = session.createQuery(sql1);
        query1.setString("out",out);
        List<Double> list = query1.list();
        Double myCash = list.get(0);
        if(myCash <= money) {
            throw new RuntimeException("账户"+out+"：余额不足，转账失败");
        }

        String sql2 = "update Account a set a.money = a.money - :money where a.name = :out";
        Query query2 = session.createQuery(sql2);
        query2.setDouble("money",money);
        query2.setString("out",out);
        query2.executeUpdate();
    }

    /**
     * @param in
     *            :转入账号
     * @param money
     *            :转账金额
     */
    public void inMoney(String in, Double money) {
        String sql = "update Account a set a.money = a.money + :money where a.name = :in";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setDouble("money",money);
        query.setString("in",in);
        query.executeUpdate();
    }
}