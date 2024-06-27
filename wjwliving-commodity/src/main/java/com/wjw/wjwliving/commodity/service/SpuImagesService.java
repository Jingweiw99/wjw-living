package com.wjw.wjwliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjw.utils.PageUtils;
import com.wjw.wjwliving.commodity.entity.SpuImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * spu 图片集
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-27 09:20:13
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveImages(Long id, List<String> images);
}

