package com.wjw.wjwliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjw.utils.PageUtils;
import com.wjw.wjwliving.commodity.entity.BrandEntity;

import java.util.Map;

/**
 * 家居品牌
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-12 14:08:56
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

