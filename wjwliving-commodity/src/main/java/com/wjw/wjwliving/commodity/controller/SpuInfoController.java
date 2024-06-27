package com.wjw.wjwliving.commodity.controller;

import java.util.Arrays;
import java.util.Map;

import com.wjw.utils.PageUtils;
import com.wjw.utils.R;
import com.wjw.wjwliving.commodity.vo.SpuSaveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wjw.wjwliving.commodity.entity.SpuInfoEntity;
import com.wjw.wjwliving.commodity.service.SpuInfoService;



/**
 * 商品 spu 信息
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-26 15:41:11
 */
@RestController
@RequestMapping("commodity/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:spuinfo:list")
    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = spuInfoService.queryPage(params);
        // 带有条件
        PageUtils page = spuInfoService.queryPageByCondition(params);
        return R.ok().put("page", page);
    }
    @PostMapping(value = "/{spuId}/up")
    public R spuUp(@PathVariable("spuId") Long spuId) {
        spuInfoService.up(spuId);
        return R.ok();
    }
    //商品下架
    @PostMapping(value = "/{spuId}/down")
    public R spuDown(@PathVariable("spuId") Long spuId) {
        spuInfoService.down(spuId);
        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("commodity:spuinfo:info")
    public R info(@PathVariable("id") Long id){
		SpuInfoEntity spuInfo = spuInfoService.getById(id);

        return R.ok().put("spuInfo", spuInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:spuinfo:save")
    public R save(@RequestBody SpuSaveVO spuSaveVO){
		spuInfoService.saveVo(spuSaveVO);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:spuinfo:update")
    public R update(@RequestBody SpuInfoEntity spuInfo){
		spuInfoService.updateById(spuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:spuinfo:delete")
    public R delete(@RequestBody Long[] ids){
		spuInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
