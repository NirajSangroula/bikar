package com.pm.niraj.bikarorderdeal.domain.entity;

import com.pm.niraj.bikarorderdeal.domain.entity.enums.DealType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"compensation"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Deal extends BaseModel{
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Offer offer;

    @Enumerated(EnumType.ORDINAL)
    DealType dealType;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "deal", orphanRemoval = true, fetch = FetchType.LAZY)
    Compensation compensation;

    public void setCompensation(Compensation compensation) {
        this.compensation = compensation;
        compensation.setDeal(this);
    }
}