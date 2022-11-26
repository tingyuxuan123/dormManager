package com.yfdl.controller;




import cn.hutool.core.util.ObjectUtil;
import com.yfdl.entity.AdminEntity;
import com.yfdl.entity.UserEntity;
import com.yfdl.service.AdminService;
import com.yfdl.service.UserService;
import com.yfdl.utils.R;
import com.yfdl.vo.LoginVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-11-23 00:13:46
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController{
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    @GetMapping("/list")
    public UserEntity list(){
        UserEntity byId = userService.getById(1L);
        return  byId;
    }

    @PostMapping("login")
    public R login(@RequestBody UserEntity user){
        UserEntity user_name = userService.query().eq("USER_NAME", user.getUSER_NAME())
                .eq("PASSWORD", user.getPASSWORD()).one();



        if(ObjectUtil.isNotNull(user_name)){
            LoginVo loginVo = new LoginVo();
            loginVo.setUsername(user_name.getUSER_NAME());
            ArrayList<String> strings = new ArrayList<>();
            if(user_name.getID().equals(1L)){
                strings.add("admin");
                loginVo.setBuildingId(0L);
            }else {
                strings.add("common");

                AdminEntity admin = adminService.getById(user_name.getID());
                if(ObjectUtil.isNotNull(admin)){
                    loginVo.setBuildingId(admin.getMANAGE_AREA());
                }
            }
            loginVo.setRoles(strings);
            loginVo.setAccessToken("token");
            loginVo.setRefreshToken("token");

            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.DAY_OF_MONTH,1);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            loginVo.setExpires(sf.format(instance.getTime()));

            return R.successResult(loginVo);
        }else {
            return R.errorResult(401,"用户名或密码错误");
        }
    }
}

