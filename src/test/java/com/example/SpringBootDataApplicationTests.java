package com.example;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class SpringBootDataApplicationTests {
    //这里就是自动帮我们创建好的对象,我们注入后就可以直接使用了!
    @Autowired
    private DataSource dataSource;
    @Autowired
    private DruidDataSource druidDataSource;


    @Test
    void contextLoads() throws SQLException {
        //class com.zaxxer.hikari.HikariDataSource:这个数据源相较于P3C0,速度是最快的!也可以理解是SpringBoot的内置数据源
        System.out.println("默认数据源==>" + dataSource.getClass());
        System.out.println("指定Druid数据源==>"+druidDataSource.getClass());
//
//        Connection connection = dataSource.getConnection();
//        System.out.println("数据库连接==>"+connection);
//        connection.close();

        int maxActive = druidDataSource.getMaxActive();
        System.out.println("Druid最大连接数==>" + maxActive);
        DruidDataSource source = (DruidDataSource) dataSource;
        System.out.println("DataSource转DruidDataSource最大连接数==>"+source.getMaxActive());
    }

}
