package com.yfdl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * (Building)表实体类
 *
 * @author makejava
 * @since 2022-11-23 00:27:30
 */
@Data
@NoArgsConstructor
@KeySequence("mybatisPlusKeyGenerator")
@TableName("YFDL.\"BUILDING\"")
public class BuildingEntity implements Serializable {
    private static final long serialVersionUID = 856441506981233989L;

    
        /**
    * id

    */
        @TableId(value = "ID", type = IdType.INPUT)
        @JsonProperty("ID")
        private Long ID;

        /**
    * 楼名
    */
        @JsonProperty("BUILD_NAME")
            private String BUILD_NAME;

        /**
    * 描述
    */
        @JsonProperty("DESCRIBE")
            private String DESCRIBE;

}
