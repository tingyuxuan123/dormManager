package com.yfdl.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * (Admin)表实体类
 *
 * @author makejava
 * @since 2022-11-23 21:10:50
 */
@Data
@NoArgsConstructor
@TableName("YFDL.\"ADMIN\"")
@KeySequence("mybatisPlusKeyGenerator")
public class AdminEntity implements Serializable {
    private static final long serialVersionUID = 215393442242732265L;

    
        /**
    * 管理员号，主键
    */
        @TableId(value = "ID", type = IdType.INPUT)
        private Long ID;

        /**
    * 姓名
    */
            private String NAME;

        /**
    * 性别
    */
            private String SEX;

        /**
    * 年龄
    */
            private Long AGE;

        /**
    * 管理区域id
    */
            private Long MANAGE_AREA;

    /**
     * 楼名
     */
    @TableField(exist = false)
    private String buildingName;

        /**
    * 电话号码
    */
            private Long TELEPHONE;

        /**
    * 家庭住址
    */
            private String ADDRESS;

            @TableField(exist = false)
            private String PASSWORD;

}
