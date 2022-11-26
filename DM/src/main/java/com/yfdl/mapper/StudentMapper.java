package com.yfdl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yfdl.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Student)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-23 00:28:05
 */
@Mapper
public interface StudentMapper extends BaseMapper<StudentEntity> {
}
