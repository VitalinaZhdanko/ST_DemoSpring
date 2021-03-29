package com.zhdanko.demospring.repository;

import com.zhdanko.demospring.entities.InsuranceClaim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceClaimRepository extends JpaRepository<InsuranceClaim, Integer> {
    List<InsuranceClaim> findAllByInsurancePolicy_Id(int id);
}
