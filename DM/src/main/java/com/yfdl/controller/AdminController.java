package com.yfdl.controller;




import com.yfdl.dto.AdminQueryParam;
import com.yfdl.dto.StudentListDto;
import com.yfdl.dto.StudentQueryParam;
import com.yfdl.entity.AdminEntity;
import com.yfdl.service.AdminService;
import com.yfdl.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2022-11-23 00:27:21
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController{
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    @PostMapping("/list")
    public R studentList(@RequestBody AdminQueryParam adminQueryParam){
        return adminService.adminList(adminQueryParam);
    }

    @PostMapping("insertAdmin")
    public R insertStudent(@RequestBody AdminEntity adminEntity){
        return adminService.insertAdmin(adminEntity);
    }

    @PostMapping("editAdmin")
    public R editStudent(@RequestBody AdminEntity adminEntity){

        return adminService.editAdmin(adminEntity);
    }

    @GetMapping("deleteAdmin")
    public R deleteStudent(@RequestParam Long id){
        return adminService.deleteAdmin(id);
    }


}

