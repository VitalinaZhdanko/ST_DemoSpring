package com.zhdanko.demospring.controller;

import com.zhdanko.demospring.entities.Car;
import com.zhdanko.demospring.entities.CarGroup;
import com.zhdanko.demospring.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/rental_car/client")
public class CarController {
    @Autowired
    CarRepository carRepository;

    @GetMapping("/cars/{carGroupID}")
    public String getAllCar(@PathVariable int carGroupID, Model model) {
        try {
            List<Car> cars = new ArrayList<>();

            cars.addAll(carRepository.findByCarGroupAndAvailableIsTrue(new CarGroup(carGroupID)));

            model.addAttribute("cars", cars);

            return "carListClient";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}