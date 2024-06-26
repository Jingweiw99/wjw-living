package com.wjw.wjwliving.commodity.service.impl;

import com.wjw.utils.PageUtils;
import com.wjw.utils.Query;
import com.wjw.wjwliving.commodity.entity.AttrEntity;
import com.wjw.wjwliving.commodity.service.AttrService;
import com.wjw.wjwliving.commodity.vo.AttrGroupWithAttrsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wjw.wjwliving.commodity.dao.AttrgroupDao;
import com.wjw.wjwliving.commodity.entity.AttrgroupEntity;
import com.wjw.wjwliving.commodity.service.AttrgroupService;

import javax.annotation.Resource;


@Service("attrgroupService")
public class AttrgroupServiceImpl extends ServiceImpl<AttrgroupDao, AttrgroupEntity> implements AttrgroupService {
    @Resource
    private AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrgroupEntity> page = this.page(
                new Query<AttrgroupEntity>().getPage(params),
                new QueryWrapper<AttrgroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long categoryId) {
        String key = (String) params.get("key");
        QueryWrapper<AttrgroupEntity> wrapper = new QueryWrapper<AttrgroupEntity>();

        if (!StringUtils.isEmpty(key)) {
            // 这样写形成的sql语句是将id name 括号在一起的
            wrapper.and((obj) -> {
                obj.eq("id", key).or().like("name", key);
            });
//            wrapper.eq("id", key).or().like("name", key);
        }
        //业务逻辑, categoryId为0，不做三级分类查询商品属性组
        if (categoryId == 0) {
            IPage<AttrgroupEntity> page =
                    this.page(new Query<AttrgroupEntity>().getPage(params), wrapper);
            return new PageUtils(page);
        } else {
            wrapper.eq("category_id", categoryId);
            IPage<AttrgroupEntity> page =
                    this.page(new Query<AttrgroupEntity>().getPage(params), wrapper);
            return new PageUtils(page);
        }
    }

    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrs(Long categoryId) {
        List<AttrgroupEntity> lists = this.list(new QueryWrapper<AttrgroupEntity>().eq("category_id", categoryId));
        List<AttrGroupWithAttrsVo> results = lists.stream().map(attrgroup -> {
            AttrGroupWithAttrsVo vo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(attrgroup, vo);
            Long id = attrgroup.getId();
            List<AttrEntity> relationAttr = attrService.getRelationAttr(id);
            vo.setAttrs(relationAttr);
            return vo;
        }).collect(Collectors.toList());
        return results;
    }

}