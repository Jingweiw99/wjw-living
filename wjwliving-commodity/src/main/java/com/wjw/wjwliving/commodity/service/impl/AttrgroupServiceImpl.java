package com.wjw.wjwliving.commodity.service.impl;

import com.wjw.utils.PageUtils;
import com.wjw.utils.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wjw.wjwliving.commodity.dao.AttrgroupDao;
import com.wjw.wjwliving.commodity.entity.AttrgroupEntity;
import com.wjw.wjwliving.commodity.service.AttrgroupService;


@Service("attrgroupService")
public class AttrgroupServiceImpl extends ServiceImpl<AttrgroupDao, AttrgroupEntity> implements AttrgroupService {

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

}