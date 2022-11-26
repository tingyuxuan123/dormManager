package com.yfdl.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * (Dormitory)表实体类
 *
 * @author makejava
 * @since 2022-11-23 00:27:45
 */
@Data
@NoArgsConstructor
@KeySequence("mybatisPlusKeyGenerator")
@TableName("YFDL.\"DORMITORY\"")
public class DormitoryEntity implements Serializable {
    private static final long serialVersionUID = -70438179235124638L;


    /**
     * 宿舍id
     * */
        @TableId(value = "ID", type = IdType.INPUT)
        @JsonProperty("ID")
        private Long ID;

        /**
        * 楼id
        */
            @JsonProperty("BUILD_ID")
            private Long BUILD_ID;


            @TableField(exist = false)
            @JsonProperty("BUILD_NAME")
            private String BUILD_NAME;

        /**
    * 人数
    */
            @JsonProperty("NUM")
            private Long NUM;

    /**
     * 宿舍名
     */
    @JsonProperty("DOR_NAME")
            private String DOR_NAME;

}
