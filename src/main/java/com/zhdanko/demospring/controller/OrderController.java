package com.zhdanko.demospring.controller;

import com.zhdanko.demospring.entities.Car;
import com.zhdanko.demospring.entities.Client;
import com.zhdanko.demospring.entities.ClientOrder;
import com.zhdanko.demospring.entities.DrivingLicense;
import com.zhdanko.demospring.repository.CarRepository;
import com.zhdanko.demospring.repository.ClientRepository;
import com.zhdanko.demospring.repository.DrivingLicenseRepository;
import com.zhdanko.demospring.repository.ManagerRepository;
import com.zhdanko.demospring.repository.OrderRepository;
import com.zhdanko.demospring.repository.StatusOrderRepository;
import com.zhdanko.demospring.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/rental_car/client")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DrivingLicenseRepository drivingLicenseRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    StatusOrderRepository statusOrderRepository;

    Car car = new Car();
    ClientOrder clientOrder = new ClientOrder();

    @GetMapping("/order/{carID}")
    public String orderForm(@PathVariable int carID, Model model) {
        carRepository.updateAvailable(false, carID);

        car.setId(carID);

        model.addAttribute("client", new Client());
        model.addAttribute("drivingLicense", new DrivingLicense());
        model.addAttribute("startRent", "");
        model.addAttribute("finishRent", "");

        return "order";
    }

    @PostMapping("/order")
    public String orderSubmit(@ModelAttribute Client client, @ModelAttribute DrivingLicense drivingLicense, @RequestParam String startRent, @RequestParam String finishRent, Model model) {
        clientRepository.save(client);

        drivingLicense.setClient(client);
        drivingLicenseRepository.save(drivingLicense);

        clientOrder.setOrderDateTime(new Timestamp(new Date().getTime()));
        clientOrder.setStartRent(new Timestamp(Util.convertToDateTime(startRent).getTime()));
        clientOrder.setFinishRent(new Timestamp(Util.convertToDateTime(finishRent).getTime()));
        clientOrder.setCar(carRepository.findById(car.getId()).get());
        clientOrder.setCost(Util.getTotalCost(Util.convertToDateTime(startRent), Util.convertToDateTime(finishRent), carRepository.findById(car.getId()).get().getCostMinute()));
        clientOrder.setClient(client);
        clientOrder.setManager(managerRepository.findById(2).get());
        clientOrder.setStatusOrder(statusOrderRepository.findById(1).get());

        orderRepository.save(clientOrder);

        return "redirect:order/result/" + clientOrder.getId();
    }

    @GetMapping("/order/result/{clientOrderID}")
    public String result(@PathVariable int clientOrderID, Model model) {
        model.addAttribute("cost", "");
        model.addAttribute("order", orderRepository.findById(clientOrderID).get());

        return "result";
    }

    @PostMapping("/order/result")
    public String orderSubmit(@RequestParam("action") String action, @RequestParam("orderID") int orderID, @RequestParam(required = false) String cost, Model model) {
        if (action.equals("Pay")) {
            if (orderRepository.findById(orderID).get().getCost() <= Double.parseDouble(cost) && orderRepository.findById(orderID).get().getStatusOrder().id == 2) {
                orderRepository.updateStatusByOrderID(statusOrderRepository.findById(5).get(), orderID);
            } else {
                model.addAttribute("message", "insufficient funds");
                return "error";
            }
        }
        if (action.equals("New order")) {
            if (orderRepository.findById(orderID).get().getStatusOrder().id == 3) {
                return "redirect:/";
            } else {
                model.addAttribute("message", "You should pay order");
                return "error";
            }
        }

        return "redirect:/";
    }


}
