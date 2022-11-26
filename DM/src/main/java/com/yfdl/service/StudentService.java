package com.yfdl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yfdl.dto.StudentListDto;
import com.yfdl.dto.StudentQueryParam;
import com.yfdl.entity.StudentEntity;
import com.yfdl.utils.R;

/**
 * (Student)表服务接口
 *
 * @author makejava
 * @since 2022-11-23 00:28:05
 */
public interface StudentService extends IService<StudentEntity> {
    R studentList(StudentQueryParam studentQueryParam);

    R insertStudent(StudentListDto studentInfo);

    R editStudent(StudentListDto studentInfo);

    R deleteStudent(Long id);
}
