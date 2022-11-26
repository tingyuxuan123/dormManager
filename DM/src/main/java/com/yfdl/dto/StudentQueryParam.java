package com.yfdl.dto;

import lombok.Data;

@Data
public class StudentQueryParam {

    private String studentName;

    private Long buildingId;

    private Long dormitoryId;

    private Long currentPage;

    private Long pageSize;


}
