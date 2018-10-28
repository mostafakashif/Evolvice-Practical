/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.task.evolvice.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author mostafa.kashif
 */
@Entity
@Table(name = "CAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c")
    , @NamedQuery(name = "Car.findByCarId", query = "SELECT c FROM Car c WHERE c.carId = :carId")
    , @NamedQuery(name = "Car.findByBrand", query = "SELECT c FROM Car c WHERE c.brand = :brand")
    , @NamedQuery(name = "Car.findByModel", query = "SELECT c FROM Car c WHERE c.model = :model")
    , @NamedQuery(name = "Car.findByYearOfProduction", query = "SELECT c FROM Car c WHERE c.yearOfProduction = :yearOfProduction")
    , @NamedQuery(name = "Car.findByCategory", query = "SELECT c FROM Car c WHERE c.category = :category")})
public class Car extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "CAR_ID", insertable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "columnId_generator")
    @SequenceGenerator(name = "columnId_generator", sequenceName = "CAR_SEQ", allocationSize = 0)
    private Integer carId;
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "BRAND")
    private String brand;
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "MODEL")
    private String model;
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "YEAR_OF_PRODUCTION")
    private String yearOfProduction;
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CATEGORY")
    private String category;

    public Car() {
    }

    public Car(Integer carId) {
        this.carId = carId;
    }

   public Car(Integer carId,String brand, String model, String yearOfProduction, String category) {
        this.carId=carId;
        this.brand = brand;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.category = category;
    }
    
     public Car( String brand, String model, String yearOfProduction, String category) {
        
        this.brand = brand;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.category = category;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(String yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carId != null ? carId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.carId == null && other.carId != null) || (this.carId != null && !this.carId.equals(other.carId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car{" + "carId=" + carId + ", brand=" + brand + ", model=" + model + ", yearOfProduction=" + yearOfProduction + ", category=" + category + '}';
    }

    
    
}
