package com.sfc.sso_server.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sequence {

    private String name;
    private long current_value;
    private long increment;

}
