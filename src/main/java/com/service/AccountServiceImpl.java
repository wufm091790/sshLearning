package com.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dao.AccountDao;
import com.domain.JsonTest;
import com.domain.Person;
import com.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @Transactional中的的属性 propagation :事务的传播行为 isolation :事务的隔离级别 readOnly :只读
 *                     rollbackFor :发生哪些异常回滚 noRollbackFor :发生哪些异常不回滚
 *                     rollbackForClassName 根据异常类名回滚
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    /**
     * @param out
     *            :转出账号
     * @param in
     *            :转入账号
     * @param money
     *            :转账金额
     */
    public void transfer(String out, String in, Double money) {
        accountDao.outMoney(out, money);
        accountDao.inMoney(in, money);

    }

    public void jsonTest() {
        List<Person> myList = new ArrayList<Person>();
        HashMap<String, String> family = new HashMap<String, String>();
        family.put("mate", "371326199104220020");
        myList.add(new Person("wufm",27,"male","371326199201216111",family));
        //myList.add(new Person("someone",27,"female","371326199104220020"));
        String jsonString = JSON.toJSONString(myList);
        System.out.println(jsonString);
        List<Person> result = (List<Person>)JSON.parseObject(jsonString,  new TypeReference<List<Person>>(){});
        System.out.println("---------------------------");
        Iterator it = result.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
        System.out.println("++++++++++++++++++++++++++++");
        JsonTest myTest = new JsonTest();
        myTest.setPersonList(myList);
        String jsonString1 = JSON.toJSONString(myTest);
        System.out.println(jsonString1);
        JsonTest result1 = (JsonTest)JSON.parseObject(jsonString1,  new TypeReference<JsonTest>(){});
        System.out.println("---------------------------");
        System.out.println(result1.getPersonList().get(0).toString());
        //System.out.println(result1.getPersonList().get(1).toString());
        //System.out.println();
    }

}