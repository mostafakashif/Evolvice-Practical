/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.task.evolvice.service;

import com.evolvice.task.evolvice.entities.Car;
import com.evolvice.task.evolvice.exceptions.CarServiceException;
import java.util.List;

/**
 *
 * @author mostafa.kashif
 */
public interface CarService {
    
    public List<Car> getCars() throws CarServiceException;
    public Car getCarById(Integer id) throws CarServiceException;
    public void updateCar(Car car) throws CarServiceException;
    public void delete(Integer id) throws CarServiceException;
    public void addCar(Car car) throws CarServiceException;
}
