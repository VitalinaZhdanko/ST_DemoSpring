package com.zhdanko.demospring.repository;

import com.zhdanko.demospring.entities.DrivingLicense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrivingLicenseRepository extends JpaRepository<DrivingLicense, Integer> {

    DrivingLicense findByClient_Id(int id);
}
