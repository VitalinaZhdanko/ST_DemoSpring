package com.zhdanko.demospring.repository;

import com.zhdanko.demospring.entities.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusOrderRepository extends JpaRepository<StatusOrder, Integer> {
}
