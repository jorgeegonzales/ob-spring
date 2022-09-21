package com.springboot.proyectspringboot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Motocicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String company;
    private String model;
    private  Integer years;

    public Motocicleta(){
    }

    public Motocicleta(Long id, String company, String model, Integer years) {
        this.id = id;
        this.company = company;
        this.model = model;
        this.years = years;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    @Override
    public String toString() {
        return "Motocicleta{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", years=" + years +
                '}';
    }
}
