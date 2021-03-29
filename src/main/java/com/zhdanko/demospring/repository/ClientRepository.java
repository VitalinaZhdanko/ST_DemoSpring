package com.zhdanko.demospring.repository;

import com.zhdanko.demospring.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
