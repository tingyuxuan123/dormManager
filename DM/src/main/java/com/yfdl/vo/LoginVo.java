package com.yfdl.vo;

import lombok.Data;

import java.util.List;

@Data
public class LoginVo {
   private String username;
   private List<String> roles;
   private Long buildingId;
   private String accessToken;
   private String refreshToken;
   private String expires;
}
