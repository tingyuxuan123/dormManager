package com.yfdl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yfdl.entity.AdminEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Admin)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-23 00:27:21
 */
@Mapper
public interface AdminMapper extends BaseMapper<AdminEntity> {
}
