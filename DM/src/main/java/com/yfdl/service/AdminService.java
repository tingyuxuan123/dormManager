package com.yfdl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yfdl.dto.AdminQueryParam;
import com.yfdl.entity.AdminEntity;
import com.yfdl.utils.R;

/**
 * (Admin)表服务接口
 *
 * @author makejava
 * @since 2022-11-23 00:27:21
 */
public interface AdminService extends IService<AdminEntity> {
    R adminList(AdminQueryParam adminQueryParam);

    R insertAdmin(AdminEntity adminEntity);

    R editAdmin(AdminEntity adminEntity);

    R deleteAdmin(Long id);
}
