package com.wxf.propertytest.entity;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
public class User implements Serializable {

    private Long id;

    private String name;

    private String password;

    private String sex;


}
