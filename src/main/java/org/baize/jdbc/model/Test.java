package org.baize.jdbc.model;

import com.alibaba.fastjson.JSON;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ModelTest test = new ModelTest();
        JdbcTemplate template = new JdbcTemplate(test);
        B b = new B();
        A a = new A();
        C c = new C();
        D d = new D();
        test.setC(c);
        test.setA(a);
        test.setB(b);
        test.setD(d);
        JdbcModel model = null;
//        long start = System.currentTimeMillis();
//        for (int i = 0;i<20;i++) {
        model = template.select(test);
            //System.out.println(model);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }
}
