package com.pm.niraj.matchbikar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Offer extends BaseModel {

    @Column(nullable = false)
    String offerType;

    long providerId;
    @Column(nullable = false)
    String status;

    @Column(nullable = false)
    @Max(100)
    String title;

    @Column(nullable = false)
    @Min(4)
    @Max(10000)
    String description;

}
