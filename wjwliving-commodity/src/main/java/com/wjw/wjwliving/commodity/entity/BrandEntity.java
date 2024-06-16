package com.wjw.wjwliving.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @NotBlank(message = "品牌名不能为空")
    private String name;
    /**
     * logo
     */
    @NotBlank(message = "logo不能为空")
    @URL(message = "无效的url")
    private String logo;
    /**
     * 说明
     */
    private String description;
    /**
     * 显示
     */
//	@TableLogic 我们这里不用逻辑删除 用于前端switch组件切换是否展示
    @NotNull(message = "显示不能为空")
    private Integer isshow;
    /**
     * 检索首字母
     */
    @NotNull(message = "首字母不能为空")
    @Pattern(regexp = "^[a-zA-Z]$", message = "首字母必须 a-z或者 A-Z 之间")
    private String firstLetter;
    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    @Min(value = 0,message = "排序值要求大于等于 0")
    private Integer sort;

}
