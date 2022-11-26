package com.yfdl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * (Student)表实体类
 *
 * @author makejava
 * @since 2022-11-23 00:28:05
 */
@Data
@NoArgsConstructor
@TableName("YFDL.\"STUDENT\"")
public class StudentEntity implements Serializable {
    private static final long serialVersionUID = 165511724263734620L;

    
        /**
        * 学生id
        */
        @JsonProperty("ID")
        private Long ID;

        /**
        * 姓名
        */
        @JsonProperty("NAME")
        private String NAME;

        /**
        * 性别
        */
        @JsonProperty("SEX")
        private String SEX;

        /**
        * 年龄
        */
        @JsonProperty("AGE")
        private Long AGE;

        /**
         * 院系
         */
        @JsonProperty("DEPARTMENT")
        private String DEPARTMENT;

        /**
        * 电话号码
        */
        @JsonProperty("TELEPHONE")
        private Long TELEPHONE;



}
