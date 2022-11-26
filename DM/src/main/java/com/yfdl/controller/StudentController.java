package com.yfdl.controller;




import com.yfdl.dto.StudentListDto;
import com.yfdl.dto.StudentQueryParam;
import com.yfdl.entity.StudentEntity;
import com.yfdl.service.StudentService;
import com.yfdl.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Student)表控制层
 *
 * @author makejava
 * @since 2022-11-23 00:28:05
 */
@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController{
    /**
     * 服务对象
     */
    @Resource
    private StudentService studentService;


    @PostMapping("/list")
    public R studentList(@RequestBody StudentQueryParam studentQueryParam){
        return studentService.studentList(studentQueryParam);
    }

    @PostMapping("insertStudent")
    public R insertStudent(@RequestBody StudentListDto studentInfo){
        return studentService.insertStudent(studentInfo);
    }

    @PostMapping("editStudent")
    public R editStudent(@RequestBody StudentListDto studentInfo){
        return studentService.editStudent(studentInfo);
    }

    @GetMapping("deleteStudent")
    public R deleteStudent(@RequestParam Long id){
        return studentService.deleteStudent(id);
    }

}

