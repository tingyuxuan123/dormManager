package com.yfdl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminQueryParam {

    private String name;

    private Long buildingId;

    private Long currentPage;

    private Long pageSize;
}
