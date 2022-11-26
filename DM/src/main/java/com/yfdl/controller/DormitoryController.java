package com.yfdl.controller;




import com.yfdl.dto.BuildingQueryParam;
import com.yfdl.dto.DormitoryQueryParam;
import com.yfdl.entity.BuildingEntity;
import com.yfdl.entity.DormitoryEntity;
import com.yfdl.service.DormitoryService;
import com.yfdl.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Dormitory)表控制层
 *
 * @author makejava
 * @since 2022-11-23 00:27:45
 */
@CrossOrigin
@RestController
@RequestMapping("/dormitory")
public class DormitoryController{
    /**
     * 服务对象
     */
    @Resource
    private DormitoryService dormitoryService;

    @GetMapping("selectList")
    public R selectList(){
        return dormitoryService.selectList();
    }

    @PostMapping("/list")
    public R studentList(@RequestBody DormitoryQueryParam dormitoryQueryParam){
        return dormitoryService.dormitoryList(dormitoryQueryParam);
    }

    @PostMapping("insertDormitory")
    public R insertStudent(@RequestBody DormitoryEntity dormitoryEntity){
        return dormitoryService.insertDormitory(dormitoryEntity);
    }

    @PostMapping("editDormitory")
    public R editStudent(@RequestBody DormitoryEntity dormitoryEntity){

        return dormitoryService.editDormitory(dormitoryEntity);
    }

    @GetMapping("deleteDormitory")
    public R deleteStudent(@RequestParam Long id){
        return dormitoryService.deleteDormitory(id);
    }
}

