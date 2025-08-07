package com.pm.niraj.bikarorderdeal.domain.entity;


import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferStatus;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer extends BaseModel{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    OfferType offerType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Provider provider;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    OfferStatus status;

    @Column(nullable = false)
    @Max(100)
    String title;

    @Column(nullable = false)
    @Min(4)
    @Max(10000)
    String description;
}
