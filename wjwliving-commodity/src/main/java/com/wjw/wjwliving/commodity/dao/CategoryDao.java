package com.wjw.wjwliving.commodity.dao;

import com.wjw.wjwliving.commodity.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类表
 * 
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-09 14:56:53
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
