package com.wjw.wjwliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjw.utils.PageUtils;
import com.wjw.wjwliving.commodity.entity.AttrEntity;

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
}

