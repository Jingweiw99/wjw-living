package com.wjw.wjwliving.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 商品分类表
 * 
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-09 14:56:53
 */
@Data
@TableName("commodity_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private Long parentId;
	/**
	 * 
	 */
	private Integer catLevel;
	/**
	 * 
	 */
	@TableLogic
	private Integer isShow;
	/**
	 * 
	 */
	private Integer sort;
	/**
	 * 
	 */
	private String icon;
	/**
	 * 
	 */
	private String proUnit;
	/**
	 * 
	 */
	private Integer proCount;
	// 值是空数组不返回
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@TableField(exist = false)
	private List<CategoryEntity> childrenCategories;
}
