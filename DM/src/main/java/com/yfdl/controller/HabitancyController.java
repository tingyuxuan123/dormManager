package com.yfdl.controller;




import com.yfdl.entity.HabitancyEntity;
import com.yfdl.service.HabitancyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Habitancy)表控制层
 *
 * @author makejava
 * @since 2022-11-23 00:27:55
 */
@RestController
@RequestMapping("/habitancy")
public class HabitancyController{
    /**
     * 服务对象
     */
    @Resource
    private HabitancyService habitancyService;


}

