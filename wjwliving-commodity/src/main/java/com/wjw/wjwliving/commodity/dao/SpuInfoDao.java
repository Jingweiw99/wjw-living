package com.wjw.wjwliving.commodity.dao;

import com.wjw.wjwliving.commodity.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品 spu 信息
 * 
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-26 15:41:11
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {

    void updaSpuStatus(Long spuId, int i);
}
