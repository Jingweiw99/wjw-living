package com.wjw.wjwliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjw.utils.PageUtils;
import com.wjw.wjwliving.commodity.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku 图片
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-27 09:54:11
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

