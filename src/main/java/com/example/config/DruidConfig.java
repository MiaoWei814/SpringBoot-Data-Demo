package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: springBoot-data
 * @description: Druid连接池全局配置文件自定义
 * @author: MiaoWei
 * @create: 2021-09-29 22:30
 **/
@Configuration //这个就是对应我们之前的一个个的Bean元素-ApplicationContext.xml中
public class DruidConfig {

    //第一步,我们需要将yaml配置文件写的参数跟当前文件进行绑定起来
    @ConfigurationProperties(prefix = "spring.datasource")  //绑定配置文件,这里需要指明前缀
    @Bean //放进容器中
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    //实现后台监控功能
    //由于这里Servlet中没有内置容器web.xml,所以我们通过这种方式进行注册
    // 1. ServletRegistrationBean:注册一个Servlet的Bean
    @Bean //写完记得要放在Bean容器中,才能被加载进去!
    public ServletRegistrationBean statViewServlet(){
        //配置一个访问路径,也就是我们配置后我们就能进入我们后台监控页面
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //配置后台监控登录账号密码
        HashMap<String, String> map = new HashMap<>();
        //这里loginUsername与loginPassword都是Druid底层写好了的,我们不能改!
        map.put("loginUsername", "admin");
        map.put("loginPassword", "123456");

        //允许谁能访问
        //allow:这是一个固定参数,value值为空表示这里是任何一个人都能访问,如果想指定本机才能访问,那么就可以写"localhost"
        map.put("allow", "");

        //禁止谁能访问
//        map.put("MiaoDaWei", "IP地址");

        registrationBean.setInitParameters(map);//设置初始化参数
        return registrationBean;
    }


    //配置 Druid 监控 之  web 监控的 filter
    //WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        //exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*,/jdbc/*");
        bean.setInitParameters(initParams);

        //"/*" 表示过滤所有请求
        bean.setUrlPatterns(Collections.singletonList("/*"));
        return bean;
    }

}
