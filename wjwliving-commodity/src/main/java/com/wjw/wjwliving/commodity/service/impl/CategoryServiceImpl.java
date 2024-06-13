package com.wjw.wjwliving.commodity.service.impl;

import com.wjw.utils.PageUtils;
import com.wjw.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wjw.wjwliving.commodity.dao.CategoryDao;
import com.wjw.wjwliving.commodity.entity.CategoryEntity;
import com.wjw.wjwliving.commodity.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public List<CategoryEntity> listTree() {
        List<CategoryEntity> entities = baseMapper.selectList(null);
        List<CategoryEntity> categoryTree = entities.stream().filter(categoryEntity -> categoryEntity.getParentId() == 0).map(
                (category) -> {
                    // 每一个一级分类设置对应的子分类
                    category.setChildrenCategories(getChildCategories(category, entities));
                    return category;
                }
        ).sorted((category1, category2) -> {
            return (category1.getSort() == null ? 0 : category1.getSort()) - (category2.getSort() == null ? 0 : category2.getSort());
        }).collect(Collectors.toList());
        return categoryTree;
    }

    private List<CategoryEntity> getChildCategories(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentId() == root.getId();
        }).map(categoryEntity -> {
        //1、找到子菜单, 并设置
            categoryEntity.setChildrenCategories(getChildCategories(categoryEntity, all));
            return categoryEntity;
        }).sorted((category1, category2) -> {
        //2、菜单的排序
            return (category1.getSort() == null ? 0 : category1.getSort()) -
                    (category2.getSort() == null ? 0 : category2.getSort());
        }).collect(Collectors.toList());
        return children;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

}