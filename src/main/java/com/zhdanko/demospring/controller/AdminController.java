package com.zhdanko.demospring.controller;

import com.zhdanko.demospring.entities.Car;
import com.zhdanko.demospring.entities.Client;
import com.zhdanko.demospring.entities.ClientOrder;
import com.zhdanko.demospring.entities.DrivingLicense;
import com.zhdanko.demospring.entities.InsuranceClaim;
import com.zhdanko.demospring.repository.CarRepository;
import com.zhdanko.demospring.repository.ClientRepository;
import com.zhdanko.demospring.repository.DrivingLicenseRepository;
import com.zhdanko.demospring.repository.InsuranceClaimRepository;
import com.zhdanko.demospring.repository.InsurancePolicyRepository;
import com.zhdanko.demospring.repository.OrderRepository;
import com.zhdanko.demospring.repository.StatusCarRepository;
import com.zhdanko.demospring.repository.StatusOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AdminController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    StatusOrderRepository statusOrderRepository;

    @Autowired
    DrivingLicenseRepository drivingLicenseRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    InsuranceClaimRepository insuranceClaimRepository;

    @Autowired
    InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    StatusCarRepository statusCarRepository;

    @GetMapping("/rental_car/admin/")
    public String getAdminPanel(Model model) {
        try {

            return "admin";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @GetMapping("/rental_car/admin/orders/page/{page}")
    public String getAllOrders(@PathVariable("page") int page, Model model) {
        try {
            PageRequest pageable = PageRequest.of(page - 1, 3);
            Page<ClientOrder> orderPage = orderRepository.findAll(pageable);
            int totalPages = orderPage.getTotalPages();

            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
            model.addAttribute("orders", orderPage.getContent());

            return "orderList";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @GetMapping("/rental_car/admin/orders/{orderID}")
    public String orderForm(@PathVariable int orderID, Model model) {

        ClientOrder order = orderRepository.findById(orderID).get();

        Client client = clientRepository.findById(order.getClient().getId()).get();

        DrivingLicense drivingLicense = drivingLicenseRepository.findByClient_Id(order.getClient().getId());

        model.addAttribute("order", order);
        model.addAttribute("client", client);
        model.addAttribute("drivingLicense", drivingLicense);
        model.addAttribute("comment", "");

        return "orderInfo";
    }

    @PostMapping("/rental_car/admin/orders/orderInfo")
    public String orderSubmit(@RequestParam("action") String action, @RequestParam("orderID") int orderID, @RequestParam(required = false) String comment, Model model) {

        if (action.equals("Submit")) {
            orderRepository.updateStatusByOrderID(statusOrderRepository.findById(2).get(), orderID);
        }
        if (action.equals("Decline") && !comment.isEmpty()) {
            carRepository.updateAvailable(true, orderRepository.findById(orderID).get().getCar().getId());
            orderRepository.updateStatusByOrderID(statusOrderRepository.findById(3).get(), orderID);

            orderRepository.updateCommentByOrderID(comment, orderID);
        }

        return "redirect:/rental_car/admin/orders/page/1";
    }

    @GetMapping("/rental_car/admin/cars/page/{page}")
    public String getAllCars(@PathVariable("page") int page, Model model) {
        try {
            PageRequest pageable = PageRequest.of(page - 1, 3);
            Page<Car> carPage = carRepository.findAll(pageable);
            int totalPages = carPage.getTotalPages();

            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
            model.addAttribute("cars", carPage.getContent());

            return "carListAdmin";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @GetMapping("/rental_car/admin/cars/{insPolicyID}")
    public String arrangeInsClaim(@PathVariable int insPolicyID, Model model) {
        try {
            List<InsuranceClaim> insuranceClaimList = new ArrayList<>();
            insuranceClaimList.addAll(insuranceClaimRepository.findAllByInsurancePolicy_Id(insPolicyID));

            model.addAttribute("insuranceClaimList", insuranceClaimList);
            model.addAttribute("insuranceClaim", new InsuranceClaim());
            model.addAttribute("insPolicyID", insPolicyID);
            model.addAttribute("available", null);

            return "insuranceClaim";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @PostMapping("/rental_car/admin/cars/insuranceClaim")
    public String submitInsuranceClaim(@ModelAttribute("available") String available, @ModelAttribute InsuranceClaim insuranceClaim, @RequestParam("insPolicyID") int insPolicyID, Model model) {
        insuranceClaim.setInsurancePolicy(insurancePolicyRepository.findById(insPolicyID).get());
        insuranceClaimRepository.save(insuranceClaim);

        if (available.equals("on")) {
            carRepository.updateStatusCar(statusCarRepository.findById(2).get(), carRepository.findCarByInsurancePolicyId(insPolicyID).getId());
            carRepository.updateAvailable(false, carRepository.findCarByInsurancePolicyId(insPolicyID).getId());
        }

        if (available.equals("")) {
            carRepository.updateStatusCar(statusCarRepository.findById(1).get(), carRepository.findCarByInsurancePolicyId(insPolicyID).getId());
            carRepository.updateAvailable(true, carRepository.findCarByInsurancePolicyId(insPolicyID).getId());
        }

        return "redirect:/rental_car/admin/cars/" + insPolicyID;
    }
}
