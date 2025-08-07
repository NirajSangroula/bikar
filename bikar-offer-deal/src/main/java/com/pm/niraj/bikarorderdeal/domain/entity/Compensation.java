package com.pm.niraj.bikarorderdeal.domain.entity;

import com.pm.niraj.bikarorderdeal.domain.entity.enums.CompensationType;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(exclude = {"deal"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Compensation extends BaseModel {
    @Enumerated(EnumType.STRING)
    private CompensationType type;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="deal_id")
    private Deal deal;

    public void setDeal(Deal deal) {
        this.deal = deal;
        deal.compensation = this;
    }
}
