package com.wjw.wjwliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjw.utils.PageUtils;
import com.wjw.wjwliving.commodity.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联表
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-18 16:42:29
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAll(CategoryBrandRelationEntity categoryBrandRelation);
}

