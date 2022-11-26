package com.yfdl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2022-11-23 00:13:48
 */
@Data
@NoArgsConstructor
@TableName("YFDL.\"USER\"")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 527999542463012902L;

    /**
    * id
    */
        private Long ID;

        private String USER_NAME;

        private String PASSWORD;

}
