package com.pm.niraj.bikarorderdeal.domain.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Provider extends BaseModel{
    String name;
    String email;
    String phoneNumber;

}
