package com.wjw.wjwliving.commodity.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.wjw.utils.PageUtils;
import com.wjw.utils.R;
import com.wjw.valid.SaveGroup;
import com.wjw.valid.UpdateGroup;
import com.wjw.valid.UpdateIsShowGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wjw.wjwliving.commodity.entity.BrandEntity;
import com.wjw.wjwliving.commodity.service.BrandService;


/**
 * 家居品牌
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-12 14:08:56
 */
@RestController
@RequestMapping("commodity/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:brand:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("commodity:brand:info")
    public R info(@PathVariable("id") Long id) {
        BrandEntity brand = brandService.getById(id);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     * 入库需要校验，
     * 每一个入库都需要这样校验，比较麻烦，而且代码可读性扩展性很差
     * 将其抽取统一管理异常，错误
     */
//    @RequestMapping("/save")
////    @RequiresPermissions("commodity:brand:save")
//    public R save(@Validated @RequestBody BrandEntity brand, BindingResult result) {
//        if (result.hasErrors()) {
//            Map<String, String> map = new HashMap<>();
//            result.getFieldErrors().forEach(item -> {
//                String message = item.getDefaultMessage();
//                String field = item.getField();
//                map.put(field, message);
//            });
//            return R.error(400, "品牌表单数据不合法").put("data", map);
//        } else {
//            brandService.save(brand);
//            return R.ok();
//        }
//
//    }
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:brand:save")
    public R save(@Validated({SaveGroup.class}) @RequestBody BrandEntity brand) {
        brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:brand:update")
    public R update(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand) {
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 修改isshow，前端传{id,isshow}
     */
    @RequestMapping("/update/isshow")
    public R updateIsShow(@Validated({UpdateIsShowGroup.class}) @RequestBody BrandEntity brand) {
        brandService.updateById(brand);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:brand:delete")
    public R delete(@RequestBody Long[] ids) {
        brandService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
