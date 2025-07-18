package com.pm.niraj.bikaruseraccount.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name="bikar_user")
@Data
public class User extends BaseModel {
    @Column(nullable = true)
    @NotNull
    @EqualsAndHashCode.Include
    @Size(min = 1, max = 50)
    private String firstName;

    @Column(nullable = false)
    @NotNull
    @EqualsAndHashCode.Include
    @Size(min = 1, max = 50)
    private String lastName;

    @Column(unique = true,  nullable = false)
    @NotNull
    @EqualsAndHashCode.Include
    @Size(min = 5, max = 50)
    private String email;

    @Column(nullable = false)
    @NotNull
    @Setter(AccessLevel.NONE)
    private String password;

    @Column(nullable = false)
    @NotNull
    private String role;
}