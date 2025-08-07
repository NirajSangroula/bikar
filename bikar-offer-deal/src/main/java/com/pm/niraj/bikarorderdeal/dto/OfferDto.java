package com.pm.niraj.bikarorderdeal.dto;

import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import com.pm.niraj.bikarorderdeal.domain.entity.Provider;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferStatus;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {
    String offerType;
    Long providerId;
    String status;
    String title;
    String description;

}
