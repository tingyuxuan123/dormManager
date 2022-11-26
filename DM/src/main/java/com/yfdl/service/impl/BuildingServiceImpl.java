package com.yfdl.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yfdl.dto.BuildingQueryParam;
import  com.yfdl.service.BuildingService;
import com.yfdl.mapper.BuildingMapper;
import com.yfdl.entity.BuildingEntity;
import com.yfdl.utils.R;
import com.yfdl.vo.PageVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Building)表服务实现类
 *
 * @author makejava
 * @since 2022-11-23 00:27:30
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, BuildingEntity> implements BuildingService {
    @Override
    public R selectList() {

      List<BuildingEntity> list= baseMapper.getSelectList();

        return R.successResult(list);
    }

    @Override
    public R buildingList(BuildingQueryParam buildingQueryParam) {

        Page<BuildingEntity> buildingEntityPage = new Page<>(buildingQueryParam.getCurrentPage(),buildingQueryParam.getPageSize());

        LambdaQueryWrapper<BuildingEntity> buildingEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();

        if(ObjectUtil.isNotNull(buildingQueryParam.getBuildingId())){
            buildingEntityLambdaQueryWrapper.eq(BuildingEntity::getID,buildingQueryParam.getBuildingId());
        }

        page(buildingEntityPage,buildingEntityLambdaQueryWrapper);

        PageVo<BuildingEntity> buildingEntityPageVo = new PageVo<>(buildingEntityPage.getRecords(), buildingEntityPage.getTotal());

        return R.successResult(buildingEntityPageVo);
    }

    @Override
    public R insertBuilding(BuildingEntity buildingEntity) {
        boolean save = save(buildingEntity);
        return R.successResult();
    }

    @Override
    public R editBuilding(BuildingEntity buildingEntity) {
        boolean b = updateById(buildingEntity);
        return R.successResult();
    }

    @Override
    public R deleteBuilding(Long id) {
        boolean b = removeById(id);
        return R.successResult();
    }
}
