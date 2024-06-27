package com.wjw.wjwliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjw.utils.PageUtils;
import com.wjw.wjwliving.commodity.entity.SpuInfoDescEntity;

import java.util.Map;

/**
 * 商品 spu 信息介绍
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-27 09:06:19
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfoDesc(SpuInfoDescEntity spuInfoDescEntity);
}

