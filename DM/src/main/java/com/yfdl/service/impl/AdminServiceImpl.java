package com.yfdl.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yfdl.dto.AdminQueryParam;
import com.yfdl.entity.BuildingEntity;
import com.yfdl.entity.UserEntity;
import  com.yfdl.service.AdminService;
import com.yfdl.mapper.AdminMapper;
import com.yfdl.entity.AdminEntity;
import com.yfdl.service.BuildingService;
import com.yfdl.service.UserService;
import com.yfdl.utils.R;
import com.yfdl.vo.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2022-11-23 00:27:21
 */

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> implements AdminService {

    @Resource
    private BuildingService buildingService;

    @Resource
    private UserService userService;

    @Override
    public R adminList(AdminQueryParam adminQueryParam) {

        Page<AdminEntity> adminEntityPage = new Page<>(adminQueryParam.getCurrentPage(), adminQueryParam.getPageSize());

        LambdaQueryWrapper<AdminEntity> adminEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();


        if(ObjectUtil.isNotNull(adminQueryParam.getName())){
            adminEntityLambdaQueryWrapper.like(AdminEntity::getNAME,adminQueryParam.getName());
        }

        if(ObjectUtil.isNotNull(adminQueryParam.getBuildingId())){
            adminEntityLambdaQueryWrapper.eq(AdminEntity::getMANAGE_AREA,adminQueryParam.getBuildingId());
        }

        page(adminEntityPage,adminEntityLambdaQueryWrapper);

        adminEntityPage.getRecords().stream().forEach(adminEntity -> {
            BuildingEntity byId = buildingService.getById(adminEntity.getMANAGE_AREA());
            adminEntity.setBuildingName(byId.getBUILD_NAME());

            UserEntity user = userService.getById(adminEntity.getID());
            if(ObjectUtil.isNotNull(user)){
                adminEntity.setPASSWORD(user.getPASSWORD());
            }


        });

        PageVo<AdminEntity> adminEntityPageVo = new PageVo<>(adminEntityPage.getRecords(), adminEntityPage.getTotal());

        return R.successResult(adminEntityPageVo);
    }

    @Override
    public R insertAdmin(AdminEntity adminEntity) {
        boolean save = save(adminEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setID(adminEntity.getID());
        userEntity.setUSER_NAME(adminEntity.getNAME());
        userEntity.setPASSWORD(adminEntity.getPASSWORD());

        userService.save(userEntity);

        return R.successResult();
    }

    @Override
    public R editAdmin(AdminEntity adminEntity) {

        boolean b = updateById(adminEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setID(adminEntity.getID());
        userEntity.setPASSWORD(adminEntity.getPASSWORD());
        boolean b1 =userService.updateById(userEntity);

        return R.successResult();
    }

    @Override
    public R deleteAdmin(Long id) {

        boolean remove = removeById(id);

        userService.removeById(id);

        return R.successResult();
    }
}
