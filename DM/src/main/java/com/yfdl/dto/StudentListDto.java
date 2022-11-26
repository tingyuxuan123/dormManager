package com.yfdl.dto;

import com.yfdl.entity.BuildingEntity;
import com.yfdl.entity.DormitoryEntity;
import com.yfdl.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentListDto {

    public Long id;

    public StudentEntity studentEntity;

    public BuildingEntity buildingEntity;

    public DormitoryEntity dormitoryEntity;

    /**
     * 床位
     */
    public String BUNK;

    public Long Total;
}
