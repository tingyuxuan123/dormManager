package com.yfdl.dto;

import lombok.Data;

@Data
public class DormitoryQueryParam {

    private Long dormitoryId;

    private Long buildingId;

    private Long currentPage;

    private Long pageSize;
}
