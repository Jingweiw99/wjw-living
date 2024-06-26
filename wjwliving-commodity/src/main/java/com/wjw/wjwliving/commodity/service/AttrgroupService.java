package com.wjw.wjwliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjw.utils.PageUtils;
import com.wjw.wjwliving.commodity.entity.AttrgroupEntity;
import com.wjw.wjwliving.commodity.vo.AttrGroupWithAttrsVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 家居商品属性分组
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-16 14:51:54
 */
public interface AttrgroupService extends IService<AttrgroupEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryPage(Map<String, Object> params, Long categoryId);

    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrs(Long categoryId);
}

