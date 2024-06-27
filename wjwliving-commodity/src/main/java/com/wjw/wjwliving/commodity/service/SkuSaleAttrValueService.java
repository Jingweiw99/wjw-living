package com.wjw.wjwliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjw.utils.PageUtils;
import com.wjw.wjwliving.commodity.entity.SkuSaleAttrValueEntity;

import java.util.Map;

/**
 * sku 的销售属性/值表
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-27 10:03:57
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

