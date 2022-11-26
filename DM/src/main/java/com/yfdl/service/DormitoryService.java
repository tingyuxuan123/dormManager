package com.yfdl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yfdl.dto.DormitoryQueryParam;
import com.yfdl.entity.DormitoryEntity;
import com.yfdl.utils.R;

/**
 * (Dormitory)表服务接口
 *
 * @author makejava
 * @since 2022-11-23 00:27:45
 */
public interface DormitoryService extends IService<DormitoryEntity> {
    R selectList();

    R dormitoryList(DormitoryQueryParam dormitoryEntity);

    R insertDormitory(DormitoryEntity dormitoryEntity);

    R editDormitory(DormitoryEntity dormitoryEntity);

    R deleteDormitory(Long id);
}
