package com.zhdanko.demospring.repository;

import com.zhdanko.demospring.entities.Car;
import com.zhdanko.demospring.entities.CarGroup;
import com.zhdanko.demospring.entities.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByCarGroupAndAvailableIsTrue(CarGroup carGroup);

    @Modifying
    @Query("update Car c set c.available=:flag where c.id=:id")
    @Transactional
    int updateAvailable(@Param("flag") boolean flag, @Param("id") int id);

    Car findCarByInsurancePolicyId(int id);

    @Modifying
    @Query("update Car c set c.carStatus=:status where c.id=:id")
    @Transactional
    int updateStatusCar(@Param("status") CarStatus status, @Param("id") int id);

}
