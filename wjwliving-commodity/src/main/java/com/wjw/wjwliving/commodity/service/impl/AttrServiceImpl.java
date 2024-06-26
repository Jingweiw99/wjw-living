package com.wjw.wjwliving.commodity.service.impl;

import com.wjw.utils.PageUtils;
import com.wjw.utils.Query;
import com.wjw.wjwliving.commodity.dao.AttrAttrgroupRelationDao;
import com.wjw.wjwliving.commodity.entity.AttrAttrgroupRelationEntity;
import com.wjw.wjwliving.commodity.service.AttrAttrgroupRelationService;
import com.wjw.wjwliving.commodity.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wjw.wjwliving.commodity.dao.AttrDao;
import com.wjw.wjwliving.commodity.entity.AttrEntity;
import com.wjw.wjwliving.commodity.service.AttrService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {
    @Resource
    private AttrAttrgroupRelationDao relationDao;
    @Resource
    private AttrAttrgroupRelationService attrAttrgroupRelationService;
    @Autowired
    private CategoryService categoryService;
    @Resource
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }


    /**
     * 保存商品属性信息，同时保存商品属性和他的商品属性组关联关系
     *
     * @param attr
     * @@Transactional 开启事务
     */
    @Transactional
    @Override
    public void saveAttrAndRelation(AttrEntity attr) {
        //1、保存基本数据
        this.save(attr);
        //2、保存关联关系, 如果是基本属性 1，并且属性组 id 不为空
        if (attr.getAttrType() == 1 && attr.getAttrGroupId() != null) {
            AttrAttrgroupRelationEntity relationEntity = new
                    AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());
            relationDao.insert(relationEntity);
        }
    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long categoryId) {
        // 商品基础属性1
        QueryWrapper<AttrEntity> queryWrapper =
                new QueryWrapper<AttrEntity>().eq("attr_type", 1);
        if (categoryId != 0) {//如果为 0, 表示不将 categoryId 作为查询条件
            queryWrapper.eq("catelog_id", categoryId);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            // 括号包起来
            queryWrapper.and((wrapper) -> {
                wrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params), queryWrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }

    @Override
    public AttrEntity getByIdWithExInfo(Long attrId) {
        Long attrGroupId = attrAttrgroupRelationService.getAttrGroupId(attrId);
        AttrEntity entity = this.getById(attrId);
        Long[] cascadedCategoryId = categoryService.getCascadedCategoryId(entity.getCategoryId());
        entity.setAttrGroupId(attrGroupId);
        entity.setCascadedCategoryId(cascadedCategoryId);
        return entity;
    }

    /**
     * 根据属性组id , 返回该属性组关联的商品属性(基本属性)
     */
    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> entities =
                attrAttrgroupRelationDao.selectList(
                        new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));
        List<Long> attrIds = entities.stream().map((item) -> {
            return item.getAttrId();
        }).collect(Collectors.toList());
        // 如果没有关联任何的基本属性，返回null
        if (attrIds == null || attrIds.size() == 0) {
            return null;
        }
        Collection<AttrEntity> attrEntities = this.listByIds(attrIds);
        return (List<AttrEntity>) attrEntities;
    }

}