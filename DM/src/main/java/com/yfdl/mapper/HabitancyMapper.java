package com.yfdl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yfdl.entity.HabitancyEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Habitancy)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-23 00:27:55
 */
@Mapper
public interface HabitancyMapper extends BaseMapper<HabitancyEntity> {
}
