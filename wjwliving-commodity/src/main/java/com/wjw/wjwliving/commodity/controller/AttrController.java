package com.wjw.wjwliving.commodity.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.wjw.utils.PageUtils;
import com.wjw.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wjw.wjwliving.commodity.entity.AttrEntity;
import com.wjw.wjwliving.commodity.service.AttrService;



/**
 * 商品属性表
 *
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-22 09:48:14
 */
@RestController
@RequestMapping("commodity/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 前端点击三级分类的菜单，分页，模糊查询
     * /1?page=1&limit=10&key=1111&attrId=xxx&attrName=xxx
     * @param params
     * @param categoryId
     * @return
     */
    @GetMapping("/base/list/{categoryId}")
    public R baseAttrList(@RequestParam Map<String, Object> params, @PathVariable("categoryId") Long categoryId){
        PageUtils page = attrService.queryBaseAttrPage(params,categoryId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
//    @RequiresPermissions("commodity:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
//		AttrEntity attr = attrService.getById(attrId);
        // 带有 attrGroupId cascadedCategoryId
        AttrEntity attr = attrService.getByIdWithExInfo(attrId);
        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:attr:save")
    public R save(@RequestBody AttrEntity attr){
//		attrService.save(attr);
        attrService.saveAttrAndRelation(attr);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:attr:update")
    public R update(@RequestBody AttrEntity attr){
		attrService.updateById(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
