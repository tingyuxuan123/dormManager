package com.yfdl.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yfdl.dto.StudentListDto;
import com.yfdl.dto.StudentQueryParam;
import com.yfdl.entity.BuildingEntity;
import com.yfdl.entity.DormitoryEntity;
import com.yfdl.entity.HabitancyEntity;
import com.yfdl.service.BuildingService;
import com.yfdl.service.DormitoryService;
import com.yfdl.service.HabitancyService;
import  com.yfdl.service.StudentService;
import com.yfdl.mapper.StudentMapper;
import com.yfdl.entity.StudentEntity;
import com.yfdl.utils.R;
import com.yfdl.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * (Student)表服务实现类
 *
 * @author makejava
 * @since 2022-11-23 00:28:05
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {

    @Resource
    private HabitancyService habitancyService;

    @Resource
    private DormitoryService dormitoryService;

    @Resource
    private BuildingService buildingService;

    @Override
    public R studentList(StudentQueryParam studentQueryParam) {

        LambdaQueryWrapper<HabitancyEntity> habitancyEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();

        if(ObjectUtil.isNotNull(studentQueryParam.getStudentName())){
            List<StudentEntity> list = lambdaQuery().like(StudentEntity::getNAME, studentQueryParam.getStudentName()).list();
            habitancyEntityLambdaQueryWrapper.in(HabitancyEntity::getSTUDENT_ID,list.stream().map(StudentEntity::getID).collect(Collectors.toList()));
        }

        if(ObjectUtil.isNotNull(studentQueryParam.getBuildingId())){
            habitancyEntityLambdaQueryWrapper.eq(HabitancyEntity::getBUILD_ID,studentQueryParam.getBuildingId());
        }

        if(ObjectUtil.isNotNull(studentQueryParam.getDormitoryId())){
            habitancyEntityLambdaQueryWrapper.eq(HabitancyEntity::getDORMITORY_ID,studentQueryParam.getDormitoryId());
        }

        Page<HabitancyEntity> habitancyEntityPage = new Page<>(studentQueryParam.getCurrentPage(), studentQueryParam.getPageSize());

        habitancyService.page(habitancyEntityPage,habitancyEntityLambdaQueryWrapper);

        List<HabitancyEntity> records = habitancyEntityPage.getRecords();

        List<StudentListDto> collect = records.stream().map(habitancyEntity -> {

            StudentListDto studentListDto = new StudentListDto();
            studentListDto.setId(habitancyEntity.getID());
            StudentEntity student = lambdaQuery().eq(StudentEntity::getID, habitancyEntity.getSTUDENT_ID()).one();
            studentListDto.setStudentEntity(student);

            DormitoryEntity dormitory = dormitoryService.lambdaQuery().eq(DormitoryEntity::getID, habitancyEntity.getDORMITORY_ID()).one();
            studentListDto.setDormitoryEntity(dormitory);

            BuildingEntity building = buildingService.lambdaQuery().eq(BuildingEntity::getID, habitancyEntity.getBUILD_ID()).one();
            studentListDto.setBuildingEntity(building);

            studentListDto.setBUNK(habitancyEntity.getBUNK());

            return studentListDto;
        }).collect(Collectors.toList());

        PageVo<StudentListDto> studentListDtoPageVo = new PageVo<StudentListDto>(collect,habitancyEntityPage.getTotal());
        return R.successResult(studentListDtoPageVo);

    }

    @Override
    @Transactional
    public R insertStudent(StudentListDto studentInfo) {

        boolean save = save(studentInfo.getStudentEntity());

        HabitancyEntity habitancyEntity = new HabitancyEntity();

        habitancyEntity.setSTUDENT_ID(studentInfo.getStudentEntity().getID());
        habitancyEntity.setBUNK(studentInfo.getBUNK());
        habitancyEntity.setBUILD_ID(studentInfo.getBuildingEntity().getID());
        habitancyEntity.setDORMITORY_ID(studentInfo.getDormitoryEntity().getID());

        boolean save1 = habitancyService.save(habitancyEntity);


        return R.successResult();
    }

    @Override
    @Transactional
    public R editStudent(StudentListDto studentInfo) {

        boolean save = updateById(studentInfo.getStudentEntity());

        HabitancyEntity habitancyEntity = new HabitancyEntity();

        habitancyEntity.setID(studentInfo.getId());
        habitancyEntity.setSTUDENT_ID(studentInfo.getStudentEntity().getID());
        habitancyEntity.setBUNK(studentInfo.getBUNK());
        habitancyEntity.setBUILD_ID(studentInfo.getBuildingEntity().getID());
        habitancyEntity.setDORMITORY_ID(studentInfo.getDormitoryEntity().getID());

        boolean save1 = habitancyService.updateById(habitancyEntity);

        return R.successResult();
    }

    @Override
    @Transactional
    public R deleteStudent(Long id) {

        HabitancyEntity habitancy = habitancyService.getById(id);

        boolean b = removeById(habitancy.getSTUDENT_ID());

        boolean b1 = habitancyService.removeById(id);

        return R.successResult();
    }
}
