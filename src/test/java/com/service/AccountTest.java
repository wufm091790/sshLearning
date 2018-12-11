package com.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.log4j.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AccountTest {

    private static Logger log = Logger.getLogger(AccountTest.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoginService loginService;

    @Test
    public void logTest() {
        log.info("info");
        log.error("error");
        log.warn("warn");
    }

    @Test
    public void transfer01() {
       accountService.transfer("aaa","bbb",100d);
    }

    @Test
    public void jsonTest() {
        accountService.jsonTest();
    }

}
