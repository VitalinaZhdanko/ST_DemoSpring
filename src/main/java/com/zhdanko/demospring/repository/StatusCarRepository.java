package com.zhdanko.demospring.repository;

import com.zhdanko.demospring.entities.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusCarRepository extends JpaRepository<CarStatus, Integer> {

}
