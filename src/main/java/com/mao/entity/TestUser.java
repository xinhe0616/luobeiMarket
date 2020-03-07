package com.mao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestUser {
    private Integer adminUserId;
    private String loginUserName;
    private String loginPassword;
    private String nickName;
    private Byte locked;
}

