package com.wjw.wjwliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjw.utils.PageUtils;
import com.wjw.wjwliving.commodity.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku 信息
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-27 09:42:42
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuInfo(SkuInfoEntity skuInfoEntity);
}

