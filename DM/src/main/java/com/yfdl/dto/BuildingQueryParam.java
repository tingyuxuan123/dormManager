package com.yfdl.dto;

import lombok.Data;

@Data
public class BuildingQueryParam {
    private Long buildingId;

    private Long currentPage;

    private Long pageSize;
}
