package com.zhdanko.demospring.repository;

import com.zhdanko.demospring.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
}
