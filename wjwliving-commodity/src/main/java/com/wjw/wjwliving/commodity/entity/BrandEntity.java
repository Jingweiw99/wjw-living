package com.wjw.wjwliving.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 家居品牌
 * 
 * @author wjw
 * @email wangjingwei0@foxmail.com
 * @date 2024-06-12 14:08:56
 */
@Data
@TableName("commodity_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 品牌名
	 */
	private String name;
	/**
	 * logo
	 */
	private String logo;
	/**
	 * 说明
	 */
	private String description;
	/**
	 * 显示
	 */
//	@TableLogic 我们这里不用逻辑删除 用于前端switch组件切换是否展示
	private Integer isshow;
	/**
	 * 检索首字母
	 */
	private String firstLetter;
	/**
	 * 排序
	 */
	private Integer sort;

}
