package com.yfdl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yfdl.entity.DormitoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Dormitory)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-23 00:27:45
 */
@Mapper
public interface DormitoryMapper extends BaseMapper<DormitoryEntity> {
    List<DormitoryEntity> getSelectList();
}
