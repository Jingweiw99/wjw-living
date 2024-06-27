package com.wjw.wjwliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjw.utils.PageUtils;
import com.wjw.wjwliving.commodity.entity.SpuInfoEntity;
import com.wjw.wjwliving.commodity.vo.SpuSaveVO;

import java.util.Map;

/**
 * 商品 spu 信息
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-26 15:41:11
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveVo(SpuSaveVO spuSaveVO);

    PageUtils queryPageByCondition(Map<String, Object> params);

    void up(Long spuId);

    void down(Long spuId);
}

