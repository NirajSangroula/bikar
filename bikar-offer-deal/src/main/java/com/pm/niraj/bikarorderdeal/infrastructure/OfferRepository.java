package com.pm.niraj.bikarorderdeal.infrastructure;

import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
