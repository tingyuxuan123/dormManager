package com.yfdl.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * (Habitancy)表实体类
 *
 * @author makejava
 * @since 2022-11-23 00:27:55
 */
@Data
@NoArgsConstructor
@KeySequence("mybatisPlusKeyGenerator")
@TableName("YFDL.\"HABITANCY\"")
public class HabitancyEntity implements Serializable {
    private static final long serialVersionUID = -66385705170603422L;

    
        /**
    * id
    */
        @TableId(value = "ID", type = IdType.INPUT)
        private Long ID;

        /**
    * 学生id
    */
            private Long STUDENT_ID;

        /**
    * 宿舍楼id
    */
            private Long BUILD_ID;

        /**
    * 宿舍id
    */
            private Long DORMITORY_ID;

        /**
    * 床位
    */
            private String BUNK;

}
