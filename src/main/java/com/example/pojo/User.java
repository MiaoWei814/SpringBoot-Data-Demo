package com.example.pojo;

import lombok.Data;

/**
 * @program: springBoot-data
 * @description:
 * @author: MiaoWei
 * @create: 2021-09-30 21:31
 **/
@Data
public class User {
    private Integer id;

    private String name;

    private String pwd;
}
