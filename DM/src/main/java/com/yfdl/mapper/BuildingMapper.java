package com.yfdl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yfdl.entity.BuildingEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Building)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-23 00:27:30
 */
@Mapper
public interface BuildingMapper extends BaseMapper<BuildingEntity> {

    List<BuildingEntity> getSelectList();


}
