package com.wjw.wjwliving.commodity.vo;

import com.wjw.wjwliving.commodity.entity.AttrEntity;
import lombok.Data;

import java.util.List;

@Data
public class AttrGroupWithAttrsVo {
    private Long id;
    private String name;
    private Integer sort;
    private String description;
    private String icon;
    private Long categoryId;
    private List<AttrEntity> attrs;
}
