package com.zhdanko.demospring.repository;

import com.zhdanko.demospring.entities.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {
}
