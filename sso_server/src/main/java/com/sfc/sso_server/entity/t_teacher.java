package com.sfc.sso_server.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class t_teacher {

    private Integer id;

    private String name;

    private Integer age;

    private List<t_group> groupList;


}