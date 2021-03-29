package com.zhdanko.demospring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    private String fio;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Column(name = "date_of_issue")
    private Date dateOfIssue;

    @Column(name = "validity_period")
    private int validityPeriod;

    @Column(name = "issued_by_whom")
    private String issuedByWhom;

    @Column(name = "residence_address")
    private String residenceAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getIndentificationNumber() {
        return identificationNumber;
    }

    public void setIndentificationNumber(String indentificationNumber) {
        this.identificationNumber = indentificationNumber;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public int getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(int validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getIssuedByWhom() {
        return issuedByWhom;
    }

    public void setIssuedByWhom(String issuedByWhom) {
        this.issuedByWhom = issuedByWhom;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }
}
