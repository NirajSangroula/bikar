package com.pm.niraj.bikarorderdeal.infrastructure;

import com.pm.niraj.bikarorderdeal.domain.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
