package com.zhdanko.demospring.controller;

import com.zhdanko.demospring.entities.CarGroup;
import com.zhdanko.demospring.repository.CarGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/rental_car/client")
public class CarGroupController {
    @Autowired
    CarGroupRepository carGroupRepository;

    @GetMapping("/groups")
    public String getAllGroups(Model model) {
        try {
            List<CarGroup> groups = new ArrayList<>();

            groups.addAll(carGroupRepository.findAll());

            model.addAttribute("groups", groups);
            return "groupList";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}