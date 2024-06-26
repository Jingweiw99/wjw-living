package com.wjw.wjwliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjw.utils.PageUtils;
import com.wjw.wjwliving.commodity.entity.AttrEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属性表
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-22 09:48:14
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttrAndRelation(AttrEntity attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long categoryId);


    AttrEntity getByIdWithExInfo(Long attrId);
    /**
     * 根据属性组id , 返回该属性组关联的商品属性(基本属性)
     */
    List<AttrEntity> getRelationAttr(Long attrgroupId);
}

