package com.wjw.wjwliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjw.utils.PageUtils;
import com.wjw.wjwliving.commodity.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 商品分类表
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-09 14:56:53
 */
@Service("categoryService")
public interface CategoryService extends IService<CategoryEntity> {
    //返回所有分类及其子分类(层级关系-即树形)
    List<CategoryEntity> listTree();

    PageUtils queryPage(Map<String, Object> params);
    // 获取三级分类的级联id
    Long[] getCascadedCategoryId(Long categoryId);
}

