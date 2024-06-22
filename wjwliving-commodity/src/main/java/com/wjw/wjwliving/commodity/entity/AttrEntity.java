package com.wjw.wjwliving.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品属性表
 * 
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-22 09:48:14
 */
@Data
@TableName("commodity_attr")
public class AttrEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 属性id
	 */
	@TableId
	private Long attrId;
	/**
	 * 属性名
	 */
	private String attrName;
	/**
	 * 是否需要检索[0-不需要,1-需要]
	 */
	private Integer searchType;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 可选值列表[用逗号分开]
	 */
	private String valueSelect;
	/**
	 * 属性类型[0-销售属性，1-基本属性]
	 */
	private Integer attrType;
	/**
	 * 启用状态[0-禁用,1-启用]
	 */
	private Long enable;
	/**
	 * 所属分类
	 */
	private Long categoryId;
	/**
	 * 这是该商品属性所关联的商品属性组 id
	 * 不在表中
	 */
	@TableField(exist = false)
	private Long attrGroupId;
	/**
	 * 三级级联的分类数组
	 */
	@TableField(exist = false)
	private Long[] cascadedCategoryId;
	/**
	 * 快速展示[是否展示在介绍上: 0-否 1-是]
	 */
	private Integer showDesc;

}
