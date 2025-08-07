package com.pm.niraj.bikarorderdeal.domain.entity.enums;

import lombok.Getter;

@Getter
public enum CompensationType {
    CASH("CASH"),
    GOODS_EXCHANGE("GOODS_EXCHANGE"),
    ONLINE_PAYMENT("ONLINE_PAYMENT"),
    BIKAR_SERVICE("BIKAR_SERVICE"),
    CREDIT("CREDIT");
    CompensationType(String type){
        this.type = type;
    }
    private final String type;
}
