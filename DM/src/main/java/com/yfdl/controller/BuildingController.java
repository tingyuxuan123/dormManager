package com.yfdl.controller;




import com.yfdl.dto.AdminQueryParam;
import com.yfdl.dto.BuildingQueryParam;
import com.yfdl.entity.AdminEntity;
import com.yfdl.entity.BuildingEntity;
import com.yfdl.service.BuildingService;
import com.yfdl.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Building)表控制层
 *
 * @author makejava
 * @since 2022-11-23 00:27:30
 */
@CrossOrigin
@RestController
@RequestMapping("/building")
public class BuildingController{
    /**
     * 服务对象
     */
    @Resource
    private BuildingService buildingService;

    @GetMapping("selectList")
    public R selectList(){
        return buildingService.selectList();
    }

    @PostMapping("/list")
    public R studentList(@RequestBody BuildingQueryParam buildingQueryParam){
        return buildingService.buildingList(buildingQueryParam);
    }

    @PostMapping("insertBuilding")
    public R insertStudent(@RequestBody BuildingEntity buildingEntity){
        return buildingService.insertBuilding(buildingEntity);
    }

    @PostMapping("editBuilding")
    public R editStudent(@RequestBody BuildingEntity buildingEntity){

        return buildingService.editBuilding(buildingEntity);
    }

    @GetMapping("deleteBuilding")
    public R deleteStudent(@RequestParam Long id){
        return buildingService.deleteBuilding(id);
    }

}

