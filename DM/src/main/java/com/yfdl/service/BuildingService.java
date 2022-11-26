package com.yfdl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yfdl.dto.BuildingQueryParam;
import com.yfdl.entity.BuildingEntity;
import com.yfdl.utils.R;

/**
 * (Building)表服务接口
 *
 * @author makejava
 * @since 2022-11-23 00:27:30
 */
public interface BuildingService extends IService<BuildingEntity> {
    R selectList();

    R buildingList(BuildingQueryParam buildingQueryParam);

    R insertBuilding(BuildingEntity buildingEntity);

    R editBuilding(BuildingEntity buildingEntity);

    R deleteBuilding(Long id);
}
