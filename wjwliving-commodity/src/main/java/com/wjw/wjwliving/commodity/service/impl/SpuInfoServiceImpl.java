package com.wjw.wjwliving.commodity.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.wjw.utils.PageUtils;
import com.wjw.utils.Query;
import com.wjw.wjwliving.commodity.entity.*;
import com.wjw.wjwliving.commodity.service.*;
import com.wjw.wjwliving.commodity.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wjw.wjwliving.commodity.dao.SpuInfoDao;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {
    @Resource
    private SpuInfoDescService spuInfoDescService;
    @Resource
    private SpuImagesService spuImagesService;
    @Resource
    private AttrService attrService;
    @Resource
    private ProductAttrValueService productAttrValueService;
    @Resource
    private SkuInfoService skuInfoService;
    @Resource
    private SkuImagesService skuImagesService;
    @Resource
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 整体的spu视图对象， 需要拆分为多个表，然后入库。
     * 功能1： 保存spu基本信息
     * 功能2： 保存spu图片描述信息
     * 功能3： 保存spu图片集信息
     * 功能4： 保存spu基本属性/规格参数
     * 功能5： 保存sku的基本信息
     * 功能6： 保存spu和sku的图片信息
     * 功能7： 保存sku的销售属性
     *
     * @param spuSaveVO
     */
    @Override
    @Transactional
    public void saveVo(SpuSaveVO spuSaveVO) {
        // 1. 保存spu基本信息
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuSaveVO, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());
        this.saveBaseSpuInfo(spuInfoEntity);
        // 2. 保存spu图片描述信息
        List<String> description = spuSaveVO.getDecript();
        SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
        spuInfoDescEntity.setSpuId(spuInfoEntity.getId());
        if (description.size() == 0) {
            spuInfoDescEntity.setDescription("default.jpg");
        } else {
            spuInfoDescEntity.setDescription(String.join(",", description));
        }
        spuInfoDescService.saveSpuInfoDesc(spuInfoDescEntity);
        // 3. 保存spu图片集信息
        List<String> images = spuSaveVO.getImages();
        spuImagesService.saveImages(spuInfoEntity.getId(), images);
        //4、保存 spu 的基本属性(一个 spu 可以有多个基本属性/规格参数):
        // commodity_product_attr_value
        List<BaseAttrs> baseAttrs = spuSaveVO.getBaseAttrs();
        List<ProductAttrValueEntity> collect = baseAttrs.stream().map(attr -> {
            ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
            valueEntity.setAttrId(attr.getAttrId());
            AttrEntity id = attrService.getById(attr.getAttrId());
            valueEntity.setAttrName(id.getAttrName());
            valueEntity.setAttrValue(attr.getAttrValues());
            valueEntity.setQuickShow(attr.getShowDesc());
            valueEntity.setSpuId(spuInfoEntity.getId());
            return valueEntity;
        }).collect(Collectors.toList());
        productAttrValueService.saveProductAttr(collect);
        //5、保存当前 spu 对应的所有 sku 信息；一个 spu 可以 对应多个 sku,组成一个可以销售的商品信息
        List<Skus> skus = spuSaveVO.getSkus();
        if (skus != null && skus.size() > 0) {
            skus.forEach(item -> {
                String defaultImg = "default.jpg";
                //json 会提交很多图片，如果是当前这个 sku 的默认图片就先保存下 url
                for (Images image : item.getImages()) {
                    if (image.getDefaultImg() == 1) {
                        defaultImg = image.getImgUrl();
                    }
                }
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item, skuInfoEntity);
                //可以从 spuInfoEntity 中获取item 中没有信息，但是 skuInfoEntity 需要的信息，
                skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
                skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);//初始化销量为 0
                skuInfoEntity.setSpuId(spuInfoEntity.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                //保存 sku 的基本信息；到 commodity_sku_info
                skuInfoService.saveSkuInfo(skuInfoEntity);

                //6. 保存 sku 的图片信息；到 commodity_sku_images start
                Long skuId = skuInfoEntity.getSkuId();
                List<SkuImagesEntity> imagesEntities =
                        item.getImages().stream().map(img -> {
                            SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                            skuImagesEntity.setSkuId(skuId);
                            skuImagesEntity.setImgUrl(img.getImgUrl());
                            skuImagesEntity.setDefaultImg(img.getDefaultImg());
                            return skuImagesEntity;
                        }).filter(entity -> {
                            //如果 image 为 empty ,就不过滤掉, 返回 true 就是需要，false 就是剔除
                            return !StringUtils.isEmpty(entity.getImgUrl());
                        }).collect(Collectors.toList());
                skuImagesService.saveBatch(imagesEntities);

                //7. 保存 sku 的销售属性-值到 commodity_sku_sale_attr_value
                List<Attr> attr = item.getAttr();
                List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities =
                        attr.stream().map(a -> {
                            SkuSaleAttrValueEntity attrValueEntity = new
                                    SkuSaleAttrValueEntity();
                            BeanUtils.copyProperties(a, attrValueEntity);
                            attrValueEntity.setSkuId(skuId);
                            return attrValueEntity;
                        }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);


            });
        }

    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
        // 按检索条件-spu 名字
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((w) -> { // 业务规定id=key值或者搜索key模糊查询
                w.eq("id", key).or().like("spu_name", key);
            });
        }
        //加入发布状态
        String status = (String) params.get("status");
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("publish_status", status);
        }
        //加入品牌 id
        String brandId = (String) params.get("brandId");
        if (!StringUtils.isEmpty(brandId) && !"0".equalsIgnoreCase(brandId)) {
            wrapper.eq("brand_id", brandId);
        }
        //加入分类 id
        String catelogId = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId)) {
            wrapper.eq("catalog_id", catelogId);
        }
        //分页查询
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params), wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public void up(Long spuId) {
        this.baseMapper.updaSpuStatus(spuId, 1);
    }

    @Override
    public void down(Long spuId) {
        this.baseMapper.updaSpuStatus(spuId, 2);
    }

    private void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity) {
        this.baseMapper.insert(spuInfoEntity);
        // this.save(spuInfoEntity);
    }

}