package com.wjw.wjwliving.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 家居商品属性分组
 * 
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-16 14:51:54
 */
@Data
@TableName("commodity_attrgroup")
public class AttrgroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 组名
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 说明
	 */
	private String description;
	/**
	 * 组图标
	 */
	private String icon;
	/**
	 * 所属分类 id
	 */
	private Long categoryId;

}
