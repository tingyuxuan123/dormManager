package com.yfdl.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yfdl.dto.DormitoryQueryParam;
import com.yfdl.entity.BuildingEntity;
import com.yfdl.service.BuildingService;
import  com.yfdl.service.DormitoryService;
import com.yfdl.mapper.DormitoryMapper;
import com.yfdl.entity.DormitoryEntity;
import com.yfdl.utils.R;
import com.yfdl.vo.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Dormitory)表服务实现类
 *
 * @author makejava
 * @since 2022-11-23 00:27:45
 */
@Service
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, DormitoryEntity> implements DormitoryService {

    @Resource
    private BuildingService buildingService;

    @Override
    public R selectList() {
     List<DormitoryEntity> list= baseMapper.getSelectList();
        return R.successResult(list);
    }

    @Override
    public R dormitoryList(DormitoryQueryParam dormitoryQueryParam) {
        Page<DormitoryEntity> dormitoryEntityPage = new Page<>(dormitoryQueryParam.getCurrentPage(), dormitoryQueryParam.getPageSize());

        LambdaQueryWrapper<DormitoryEntity>  dormitoryEntityLambdaQueryWrapper= new LambdaQueryWrapper<>();

        if(ObjectUtil.isNotNull(dormitoryQueryParam.getBuildingId())){
            dormitoryEntityLambdaQueryWrapper.eq(DormitoryEntity::getBUILD_ID,dormitoryQueryParam.getBuildingId());
        }

        if(ObjectUtil.isNotNull(dormitoryQueryParam.getDormitoryId())){
            dormitoryEntityLambdaQueryWrapper.eq(DormitoryEntity::getID,dormitoryQueryParam.getDormitoryId());
        }

        page(dormitoryEntityPage,dormitoryEntityLambdaQueryWrapper);

        dormitoryEntityPage.getRecords().stream().forEach(dormitoryEntity -> {

            BuildingEntity building = buildingService.getById(dormitoryEntity.getBUILD_ID());
            dormitoryEntity.setBUILD_NAME(building.getBUILD_NAME());

        });

        PageVo<DormitoryEntity> dormitoryEntityPageVo = new PageVo<>(dormitoryEntityPage.getRecords(), dormitoryEntityPage.getTotal());

        return R.successResult(dormitoryEntityPageVo);
    }

    @Override
    public R insertDormitory(DormitoryEntity dormitoryEntity) {
        save(dormitoryEntity);

        return R.successResult();
    }

    @Override
    public R editDormitory(DormitoryEntity dormitoryEntity) {
        updateById(dormitoryEntity);
        return R.successResult();
    }

    @Override
    public R deleteDormitory(Long id) {
        removeById(id);
        return R.successResult();
    }

}
