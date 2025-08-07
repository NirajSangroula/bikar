package com.pm.niraj.bikarorderdeal.domain.entity.enums;

import lombok.Getter;

@Getter
public enum OfferType {
    ONE_TIME("ONE_TIME"),
    RECURRING("RECURRING"),
    CREDITABLE("CREDITABLE");
    private final String offerType;
    OfferType(final String offerType) {
        this.offerType = offerType;
    }
}
