package com.yfdl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.yfdl.service.HabitancyService;
import com.yfdl.mapper.HabitancyMapper;
import com.yfdl.entity.HabitancyEntity;
import org.springframework.stereotype.Service;

/**
 * (Habitancy)表服务实现类
 *
 * @author makejava
 * @since 2022-11-23 00:27:55
 */
@Service
public class HabitancyServiceImpl extends ServiceImpl<HabitancyMapper, HabitancyEntity> implements HabitancyService {
}
