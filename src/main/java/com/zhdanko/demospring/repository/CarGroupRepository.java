package com.zhdanko.demospring.repository;

import com.zhdanko.demospring.entities.CarGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarGroupRepository extends JpaRepository<CarGroup, Integer> {
}
