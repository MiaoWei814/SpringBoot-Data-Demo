package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: springBoot-data
 * @description:
 * @author: MiaoWei
 * @create: 2021-09-30 21:33
 **/
@Repository //用@Compont也是可以,但是还是要分层思想
@Mapper  //这个注解表示了这是一个mybatis的mapper类
//也可以在启动类上加上注解 @MapperScan("mapper所在的包路径"),就会去扫描指定包下的mapper都会被加载进去
public interface UserMapper {
    List<User> queryList();
}
