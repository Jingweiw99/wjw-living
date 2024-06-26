package com.wjw.wjwliving.commodity.service.impl;

import com.wjw.utils.PageUtils;
import com.wjw.utils.Query;
import com.wjw.wjwliving.commodity.dao.BrandDao;
import com.wjw.wjwliving.commodity.dao.CategoryDao;
import com.wjw.wjwliving.commodity.entity.BrandEntity;
import com.wjw.wjwliving.commodity.entity.CategoryEntity;
import com.wjw.wjwliving.commodity.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wjw.wjwliving.commodity.dao.CategoryBrandRelationDao;
import com.wjw.wjwliving.commodity.entity.CategoryBrandRelationEntity;
import com.wjw.wjwliving.commodity.service.CategoryBrandRelationService;

import javax.annotation.Resource;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {
    @Autowired
    private CategoryDao categoryDao;
    @Resource
    private BrandDao brandDao;
    @Resource
    private CategoryBrandRelationDao relationDao;
    @Resource
    private BrandService brandService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveAll(CategoryBrandRelationEntity categoryBrandRelation) {
        Long categoryId = categoryBrandRelation.getCategoryId();
        Long brandId = categoryBrandRelation.getBrandId();
        CategoryEntity categoryEntity = categoryDao.selectById(categoryId);
        BrandEntity brandEntity = brandDao.selectById(brandId);
        categoryBrandRelation.setCategoryName(categoryEntity.getName());
        categoryBrandRelation.setBrandName(brandEntity.getName());
        this.save(categoryBrandRelation);
    }

    @Override
    public List<BrandEntity> getInfoByCatId(Long catId) {
        List<CategoryBrandRelationEntity> categoryBrandRelationEntities = relationDao.selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("category_id", catId));
        List<BrandEntity> brandEntities = categoryBrandRelationEntities.stream().map(item -> {
            Long brandId = item.getBrandId();
            BrandEntity byId = brandService.getById(brandId);
            return byId;
        }).collect(Collectors.toList());
        return brandEntities;
    }

}