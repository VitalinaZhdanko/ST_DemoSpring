package com.zhdanko.demospring.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String number;

    private String name;

    @Column(name = "win_number")
    private String winNumber;

    @Column(name = "year_of_release")
    private int yearOfRelease;

    @Column(name = "cost_minute")
    private double costMinute;

    @Column(name = "is_available")
    private boolean available;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_group_id")
    private CarGroup carGroup;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_brand_id")
    private CarBrand carBrand;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_car_id")
    private CarStatus carStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "insurance_policy_id")
    private InsurancePolicy insurancePolicy;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "technical_inspection_id")
    private TechnicalInspection technicalInspection;

    public Car(int id) {
        this.id = id;
    }

    public Car() {

    }

    public Car(Integer id, String number, String name, String winNumber, int yearOfRelease, double costMinute, boolean available, CarGroup carGroup, CarBrand carBrand, CarStatus carStatus, InsurancePolicy insurancePolicy, TechnicalInspection technicalInspection) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.winNumber = winNumber;
        this.yearOfRelease = yearOfRelease;
        this.costMinute = costMinute;
        this.available = available;
        this.carGroup = carGroup;
        this.carBrand = carBrand;
        this.carStatus = carStatus;
        this.insurancePolicy = insurancePolicy;
        this.technicalInspection = technicalInspection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWinNumber() {
        return winNumber;
    }

    public void setWinNumber(String winNumber) {
        this.winNumber = winNumber;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public double getCostMinute() {
        return costMinute;
    }

    public void setCostMinute(double costMinute) {
        this.costMinute = costMinute;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        available = available;
    }

    public CarGroup getCarGroup() {
        return carGroup;
    }

    public void setCarGroup(CarGroup carGroup) {
        this.carGroup = carGroup;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public TechnicalInspection getTechnicalInspection() {
        return technicalInspection;
    }

    public void setTechnicalInspection(TechnicalInspection technicalInspection) {
        this.technicalInspection = technicalInspection;
    }
}
