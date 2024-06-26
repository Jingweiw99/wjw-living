package com.wjw.wjwliving.commodity.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wjw.utils.PageUtils;
import com.wjw.utils.R;
import com.wjw.wjwliving.commodity.service.CategoryService;
import com.wjw.wjwliving.commodity.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wjw.wjwliving.commodity.entity.AttrgroupEntity;
import com.wjw.wjwliving.commodity.service.AttrgroupService;


/**
 * 家居商品属性分组
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-16 14:51:54
 */
@RestController
@RequestMapping("commodity/attrgroup")
public class AttrgroupController {
    @Autowired
    private AttrgroupService attrgroupService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrgroupService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 条件分页查询 检索条件 点击三级分类带有categoryId
     */
    @RequestMapping("/list/{categoryId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("categoryId") Long categoryId) {
        PageUtils page = attrgroupService.queryPage(params, categoryId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("commodity:attrgroup:info")
    public R info(@PathVariable("id") Long id) {
        AttrgroupEntity attrgroup = attrgroupService.getById(id);
        // 获取改属性分组对应的categoryId
        Long categoryId = attrgroup.getCategoryId();
        Long[] cascadedCategoryId = categoryService.getCascadedCategoryId(categoryId);
        attrgroup.setCascadedCategoryId(cascadedCategoryId);
        return R.ok().put("attrgroup", attrgroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:attrgroup:save")
    public R save(@RequestBody AttrgroupEntity attrgroup) {
        attrgroupService.save(attrgroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:attrgroup:update")
    public R update(@RequestBody AttrgroupEntity attrgroup) {
        attrgroupService.updateById(attrgroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:attrgroup:delete")
    public R delete(@RequestBody Long[] ids) {
        attrgroupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 根据categoryId获取属性组带有属性
     */
    @RequestMapping("/{categoryId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("categoryId") Long categoryId) {
        List<AttrGroupWithAttrsVo> data = attrgroupService.getAttrGroupWithAttrs(categoryId);
        return R.ok().put("data", data);
    }
}
