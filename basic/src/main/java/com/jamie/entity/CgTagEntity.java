package com.jamie.entity;

import lombok.Data;

/**
 * @author ZJM
 * @date 2021/9/22 15:38
 */
@Data
public class CgTagEntity  {
    private Long pid ;
    private String theme ;
    private String type ;
    private String name ;

    public CgTagEntity(String theme, String type, String name) {
        this.theme = theme;
        this.type = type;
        this.name = name;
    }
}
